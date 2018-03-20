package org.pitest.mutationtest.engine.gregor.mutators;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.MutationIdentifier;
import org.pitest.mutationtest.engine.gregor.AbstractInsnMutator;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;
import org.pitest.mutationtest.engine.gregor.InsnSubstitution;
import org.pitest.mutationtest.engine.gregor.ZeroOperandMutation;

import java.util.HashMap;
import java.util.Map;



public class AODMutator {
 /**
  * Mutator that replaces (a+b) (a-b) (a*b) (a\b) (a%b) by a, it means that we remove the second operator;
  */ 
 public enum AODMutator1 implements MethodMutatorFactory {

   AOD_MUTATOR;

   @Override
   public MethodVisitor create(final MutationContext context,
                               final MethodInfo methodInfo,
                               final MethodVisitor methodVisitor)  {
    return new AODMethodVisitor1(this, methodInfo, context, methodVisitor);
   }

   public String getGloballyUniqueId()  {
    return this.getClass().getName();
   }

   public String getName()  {
    return name();
   }
 }
 
 /**
  * Mutator that replaces (a+b) (a-b) (a*b) (a\b) (a%b) by b
  */  
 public enum AODMutator2 implements MethodMutatorFactory {

    AOD_MUTATOR;

    @Override
    public MethodVisitor create(final MutationContext context,
                                final MethodInfo methodInfo,
                                final MethodVisitor methodVisitor)  {
     return new AODMethodVisitor2(this, context, methodVisitor);
    }

    public String getGloballyUniqueId()  {
     return this.getClass().getName();
    }

    public String getName()  {
     return name();
    }
  }
}


class AODMethodVisitor1 extends AbstractInsnMutator {
 AODMethodVisitor1(final MethodMutatorFactory factory,
                       final MethodInfo methodInfo,
                       final MutationContext context,
                       final MethodVisitor writer) {
  super(factory, methodInfo, context, writer);
 }

 private static final Map<Integer, ZeroOperandMutation> MUTATIONS = new HashMap<>();

 static {
  // https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-2.html#jvms-2.11.1
  // Long and double are 2nd category, so that using POP2
  // ADD 
  MUTATIONS.put(Opcodes.IADD, new InsnSubstitution(Opcodes.POP, "AOD Mutator: Removed the second operator from an addition formula (int)"));
  MUTATIONS.put(Opcodes.DADD, new InsnSubstitution(Opcodes.POP2, "AOD Mutator: Removed the second operator from an addition formula (double)"));
  MUTATIONS.put(Opcodes.FADD, new InsnSubstitution(Opcodes.POP, "AOD Mutator: Removed the second operator from an addition formula (float)"));
  MUTATIONS.put(Opcodes.LADD, new InsnSubstitution(Opcodes.POP2, "AOD Mutator: Removed the second operator from an addition formula (long)"));

  // SUB
  MUTATIONS.put(Opcodes.ISUB, new InsnSubstitution(Opcodes.POP, "AOD Mutator: Removed the second operator from a subtraction formula (int)"));
  MUTATIONS.put(Opcodes.DSUB, new InsnSubstitution(Opcodes.POP2, "AOD Mutator: Removed the second operator from a subtraction formula (double)"));
  MUTATIONS.put(Opcodes.FSUB, new InsnSubstitution(Opcodes.POP, "AOD Mutator: Removed the second operator from a subtraction formula (float)"));
  MUTATIONS.put(Opcodes.LSUB, new InsnSubstitution(Opcodes.POP2, "AOD Mutator: Removed the second operator from a subtraction formula (long)"));

  // MUL
  MUTATIONS.put(Opcodes.IMUL, new InsnSubstitution(Opcodes.POP, "AOD Mutator: Removed the second operator from a multiplication formula (int)"));
  MUTATIONS.put(Opcodes.DMUL, new InsnSubstitution(Opcodes.POP2, "AOD Mutator: Removed the second operator from a multiplication formula (double)"));
  MUTATIONS.put(Opcodes.FMUL, new InsnSubstitution(Opcodes.POP, "AOD Mutator: Removed the second operator from a multiplication formula (float)"));
  MUTATIONS.put(Opcodes.LMUL, new InsnSubstitution(Opcodes.POP2, "AOD Mutator: Removed the second operator from a multiplication formula (long)"));

  // DIV
  MUTATIONS.put(Opcodes.IDIV, new InsnSubstitution(Opcodes.POP, "AOD Mutator: Removed the second operator from a division formula (int)"));
  MUTATIONS.put(Opcodes.DDIV, new InsnSubstitution(Opcodes.POP2, "AOD Mutator: Removed the second operator from a division formula (double)"));
  MUTATIONS.put(Opcodes.FDIV, new InsnSubstitution(Opcodes.POP, "AOD Mutator: Removed the second operator from a division formula (float)"));
  MUTATIONS.put(Opcodes.LDIV, new InsnSubstitution(Opcodes.POP2, "AOD Mutator: Removed the second operator from a division formula (long)"));

  // MOD
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

class AODMethodVisitor2 extends MethodVisitor {
 private final MethodMutatorFactory factory;
 private final MutationContext context;
    
 AODMethodVisitor2(final MethodMutatorFactory factory, 
             final MutationContext context, 
             final MethodVisitor methodVisitor) {
     super(Opcodes.ASM6, methodVisitor);
     this.factory = factory;
     this.context = context;
 }
 
 @Override
 public void visitInsn(int opcode) {
  // ADD
  if (opcode == Opcodes.IADD) {
   replaceOperator(opcode, "AOD Mutator: Removed the first operator from an addition formula (int)", 1);
   return;
  }
  if (opcode == Opcodes.FADD) {
   replaceOperator(opcode, "AOD Mutator: Removed the first operator from an addition formula (float)", 1);
   return;
  }
  if (opcode == Opcodes.DADD) {
   replaceOperator(opcode, "AOD Mutator: Removed the first operator from an addition formula (double)", 2);
   return;
  }
  if (opcode == Opcodes.LADD) {
   replaceOperator(opcode, "AOD Mutator: Removed the first operator from an addition formula (long)", 2);
   return;
  }
  
  // SUB
  if (opcode == Opcodes.ISUB) {
   replaceOperator(opcode, "AOD Mutator: Removed the first operator from a subtraction formula (int)", 1);
   return;
  }
  if (opcode == Opcodes.FSUB) {
   replaceOperator(opcode, "AOD Mutator: Removed the first operator from a subtraction formula (float)", 1);
   return;
  }
  if (opcode == Opcodes.DSUB) {
   replaceOperator(opcode, "AOD Mutator: Removed the first operator from a subtraction formula (double)", 2);
   return;
  }
  if (opcode == Opcodes.LSUB) {
   replaceOperator(opcode, "AOD Mutator: Removed the first operator from a subtraction formula (long)", 2);
   return;
  }
  
  // MUL
  if (opcode == Opcodes.IMUL) {
   replaceOperator(opcode, "AOD Mutator: Removed the first operator from a multiplication formula (int)", 1);
   return;
  }
  if (opcode == Opcodes.FMUL) {
   replaceOperator(opcode, "AOD Mutator: Removed the first operator from a multiplication formula (float)", 1);
   return;
  }
  if (opcode == Opcodes.DMUL) {
   replaceOperator(opcode, "AOD Mutator: Removed the first operator from a multiplication formula (double)", 2);
   return;
  }
  if (opcode == Opcodes.LMUL) {
   replaceOperator(opcode, "AOD Mutator: Removed the first operator from a multiplication formula (long)", 2);
   return;
  }
  
  // DIV
  if (opcode == Opcodes.IDIV) {
   replaceOperator(opcode, "AOD Mutator: Removed the first operator from a division formula (int)", 1);
   return;
  }
  if (opcode == Opcodes.FDIV) {
   replaceOperator(opcode, "AOD Mutator: Removed the first operator from a division formula (float)", 1);
   return;
  }
  if (opcode == Opcodes.DDIV) {
   replaceOperator(opcode, "AOD Mutator: Removed the first operator from a division formula (double)", 2);
   return;
  }
  if (opcode == Opcodes.LDIV) {
   replaceOperator(opcode, "AOD Mutator: Removed the first operator from a division formula (long)", 2);
   return;
  }  
  
  // MOD
  if (opcode == Opcodes.IREM) {
   replaceOperator(opcode, "AOD Mutator: Removed the first operator from a modulus formula (int)", 1);
   return;
  }
  if (opcode == Opcodes.FREM) {
   replaceOperator(opcode, "AOD Mutator: Removed the first operator from a modulus formula (float)", 1);
   return;
  }
  if (opcode == Opcodes.DREM) {
   replaceOperator(opcode, "AOD Mutator: Removed the first operator from a modulus formula (double)", 2);
   return;
  }
  if (opcode == Opcodes.LREM) {
   replaceOperator(opcode, "AOD Mutator: Removed the first operator from a modulus formula (long)", 2);
   return;
  } 
  super.visitInsn(opcode);
 }
 
 private void replaceOperator(int opcode, String message, int type) {
   final MutationIdentifier muID = this.context.registerMutation(factory, message);
   if (this.context.shouldMutate(muID)) {
     super.visitInsn(Opcodes.SWAP);
     
     if (type == 1) {
       super.visitInsn(Opcodes.POP);
     } else { 
       super.visitInsn(Opcodes.POP2);
     }
     
    } else {
      super.visitInsn(opcode);
    }
 }
}