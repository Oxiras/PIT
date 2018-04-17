package org.pitest.mutationtest.engine.gregor.mutators.experimental;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.MutationIdentifier;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;

public enum NegateVariableMutator implements MethodMutatorFactory {

  NEGATE_VARIABLE_MUTATOR;

  @Override
  public MethodVisitor create(final MutationContext context,
      final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
    return new NegateVariableMethodVisitor(this, context, methodVisitor);
  }

  @Override
  public String getGloballyUniqueId() {
    return this.getClass().getName();
  }

  @Override
  public String getName() {
    return name();
  }
}

class NegateVariableMethodVisitor extends MethodVisitor {

  private final MethodMutatorFactory factory;
  private final MutationContext      context;

  NegateVariableMethodVisitor(final MethodMutatorFactory factory,
      final MutationContext context, final MethodVisitor delegateMethodVisitor) {
    super(Opcodes.ASM6, delegateMethodVisitor);
    this.factory = factory;
    this.context = context;
  }
  
  @Override
  public void visitVarInsn(final int opcode, final int var) {
      if (opcode == Opcodes.ILOAD) {
        final MutationIdentifier newId = this.context.registerMutation(
            this.factory, "Negated integer variable");
        mv.visitVarInsn(opcode, var);
        mv.visitInsn(Opcodes.ICONST_M1);
        mv.visitInsn(Opcodes.IMUL);
        mv.visitVarInsn(Opcodes.ISTORE, var);
        super.visitVarInsn(opcode, var);
      } else if (opcode == Opcodes.DLOAD) {
        final MutationIdentifier newId = this.context.registerMutation(
            this.factory, "Negated double variable");
        mv.visitVarInsn(opcode, var);
        mv.visitLdcInsn(new Double("-1.0"));
        mv.visitInsn(Opcodes.DMUL);
        mv.visitVarInsn(Opcodes.DSTORE, var);
        super.visitVarInsn(opcode, var);
      } else if (opcode == Opcodes.LLOAD) {
        final MutationIdentifier newId = this.context.registerMutation(
            this.factory, "Negated long variable");
        mv.visitVarInsn(opcode, var);
        mv.visitLdcInsn(new Long(-1L));
        mv.visitInsn(Opcodes.LMUL);
        mv.visitVarInsn(Opcodes.LSTORE, var);
        super.visitVarInsn(opcode, var);
      } else if (opcode == Opcodes.FLOAD) {
        final MutationIdentifier newId = this.context.registerMutation(
            this.factory, "Negated float variable");
        mv.visitVarInsn(opcode, var);
        mv.visitLdcInsn(new Float("-1.0"));
        mv.visitInsn(Opcodes.FMUL);
        mv.visitVarInsn(Opcodes.FSTORE, var);
        super.visitVarInsn(opcode, var);
      } else {
        super.visitVarInsn(opcode, var);
      }
  }

}