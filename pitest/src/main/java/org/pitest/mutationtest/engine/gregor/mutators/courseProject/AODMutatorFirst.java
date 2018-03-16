package org.pitest.mutationtest.engine.gregor.mutators.courseProject;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.gregor.AbstractInsnMutator;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;
import org.pitest.mutationtest.engine.gregor.InsnSubstitution;
import org.pitest.mutationtest.engine.gregor.ZeroOperandMutation;

import java.util.HashMap;
import java.util.Map;

/**
 * Mutator that replaces (a+b) (a-b) (a*b) (a%b) by a, it means that we remove the second operand;
 */
public enum AODMutatorFirst implements MethodMutatorFactory {

 AOD_MUTATOR_FIRST;

 @Override
 public MethodVisitor create(final MutationContext context,
                             final MethodInfo methodInfo,
                             final MethodVisitor methodVisitor)  {
  return new AODMethodVisitorFirst(this, methodInfo, context, methodVisitor);
 }

 public String getGloballyUniqueId()  {
  return this.getClass().getName();
 }

 public String getName()  {
  return name();
 }
}
class AODMethodVisitorFirst extends AbstractInsnMutator {
 AODMethodVisitorFirst(final MethodMutatorFactory factory,
                       final MethodInfo methodInfo,
                       final MutationContext context,
                       final MethodVisitor writer) {
  super(factory, methodInfo, context, writer);
 }

 private static final Map<Integer, ZeroOperandMutation> MUTATIONS = new HashMap<>();

 static {
  // https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-2.html#jvms-2.11.1
  // Long and double are 2nd category, so that using POP2
  MUTATIONS.put(Opcodes.IADD, new InsnSubstitution(Opcodes.POP, "AOD Mutator: Removed the second operator from an addition formula (int)"));
  MUTATIONS.put(Opcodes.DADD, new InsnSubstitution(Opcodes.POP2, "AOD Mutator: Removed the second operator from an addition formula (double)"));
  MUTATIONS.put(Opcodes.FADD, new InsnSubstitution(Opcodes.POP, "AOD Mutator: Removed the second operator from an addition formula (float)"));
  MUTATIONS.put(Opcodes.LADD, new InsnSubstitution(Opcodes.POP2, "AOD Mutator: Removed the second operator from an addition formula (long)"));

  MUTATIONS.put(Opcodes.ISUB, new InsnSubstitution(Opcodes.POP, "AOD Mutator: Removed the second operator from a subtraction formula (int)"));
  MUTATIONS.put(Opcodes.DSUB, new InsnSubstitution(Opcodes.POP2, "AOD Mutator: Removed the second operator from a subtraction formula (double)"));
  MUTATIONS.put(Opcodes.FSUB, new InsnSubstitution(Opcodes.POP, "AOD Mutator: Removed the second operator from a subtraction formula (float)"));
  MUTATIONS.put(Opcodes.LSUB, new InsnSubstitution(Opcodes.POP2, "AOD Mutator: Removed the second operator from a subtraction formula (long)"));

  MUTATIONS.put(Opcodes.IMUL, new InsnSubstitution(Opcodes.POP, "AOD Mutator: Removed the second operator from a multiplication formula (int)"));
  MUTATIONS.put(Opcodes.DMUL, new InsnSubstitution(Opcodes.POP2, "AOD Mutator: Removed the second operator from a multiplication formula (double)"));
  MUTATIONS.put(Opcodes.FMUL, new InsnSubstitution(Opcodes.POP, "AOD Mutator: Removed the second operator from a multiplication formula (float)"));
  MUTATIONS.put(Opcodes.LMUL, new InsnSubstitution(Opcodes.POP2, "AOD Mutator: Removed the second operator from a multiplication formula (long)"));

  MUTATIONS.put(Opcodes.IDIV, new InsnSubstitution(Opcodes.POP, "AOD Mutator: Removed the second operator from a division formula (int)"));
  MUTATIONS.put(Opcodes.DDIV, new InsnSubstitution(Opcodes.POP2, "AOD Mutator: Removed the second operator from a division formula (double)"));
  MUTATIONS.put(Opcodes.FDIV, new InsnSubstitution(Opcodes.POP, "AOD Mutator: Removed the second operator from a division formula (float)"));
  MUTATIONS.put(Opcodes.LDIV, new InsnSubstitution(Opcodes.POP2, "AOD Mutator: Removed the second operator from a division formula (long)"));

  MUTATIONS.put(Opcodes.IREM, new InsnSubstitution(Opcodes.POP, "AOD Mutator: Removed the second operator from a modulus formula (int)"));
  MUTATIONS.put(Opcodes.DREM, new InsnSubstitution(Opcodes.POP2, "AOD Mutator: Removed the second operator from a modulus formula (double)"));
  MUTATIONS.put(Opcodes.FREM, new InsnSubstitution(Opcodes.POP, "AOD Mutator: Removed the second operator from a modulus formula (float)"));
  MUTATIONS.put(Opcodes.LREM, new InsnSubstitution(Opcodes.POP2, "AOD Mutator: Removed the second operator from a modulus formula (long)"));
 }

 @Override
 protected Map<Integer, ZeroOperandMutation> getMutations() {
  return MUTATIONS;
 }
}