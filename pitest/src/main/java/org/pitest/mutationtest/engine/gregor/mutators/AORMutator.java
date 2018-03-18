package org.pitest.mutationtest.engine.gregor.mutators;

import java.util.HashMap;
import java.util.Map;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.gregor.AbstractInsnMutator;
import org.pitest.mutationtest.engine.gregor.InsnSubstitution;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;
import org.pitest.mutationtest.engine.gregor.ZeroOperandMutation;

public class AORMutator {
    public enum MathMutator1 implements MethodMutatorFactory {

    AOR_MUTATOR;

      @Override
      public MethodVisitor create(final MutationContext context,
          final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
        return new MathMethodVisitorC1(this, methodInfo, context, methodVisitor);
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
    
    public enum MathMutator2 implements MethodMutatorFactory {

    AOR_MUTATOR;

      @Override
      public MethodVisitor create(final MutationContext context,
          final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
        return new MathMethodVisitorC2(this, methodInfo, context, methodVisitor);
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
    
    public enum MathMutator3 implements MethodMutatorFactory {

    AOR_MUTATOR;

      @Override
      public MethodVisitor create(final MutationContext context,
          final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
        return new MathMethodVisitorC3(this, methodInfo, context, methodVisitor);
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
    
    public enum MathMutator4 implements MethodMutatorFactory {

    AOR_MUTATOR;
      
      @Override
      public MethodVisitor create(final MutationContext context,
          final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
        return new MathMethodVisitorC4(this, methodInfo, context, methodVisitor);
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
}

class MathMethodVisitorC1 extends AbstractInsnMutator {

  MathMethodVisitorC1(final MethodMutatorFactory factory,
      final MethodInfo methodInfo, final MutationContext context,
      final MethodVisitor writer) {
    super(factory, methodInfo, context, writer);
  }

 private static final Map<Integer, ZeroOperandMutation> MUTATIONS = new HashMap<>();

  static {
    MUTATIONS.put(Opcodes.IADD, new InsnSubstitution(Opcodes.ISUB,"AOR Mutator: Replaced integer addition with subtraction"));
    MUTATIONS.put(Opcodes.ISUB, new InsnSubstitution(Opcodes.IADD,"AOR Mutator: Replaced integer subtraction with addition"));
    MUTATIONS.put(Opcodes.IMUL, new InsnSubstitution(Opcodes.IDIV,"AOR Mutator: Replaced integer multiplication with division"));
    MUTATIONS.put(Opcodes.IDIV, new InsnSubstitution(Opcodes.IMUL,"AOR Mutator: Replaced integer division with multiplication"));
    MUTATIONS.put(Opcodes.IOR, new InsnSubstitution(Opcodes.IAND,"AOR Mutator: Replaced bitwise OR with AND"));
    MUTATIONS.put(Opcodes.IAND, new InsnSubstitution(Opcodes.IOR,"AOR Mutator: Replaced bitwise AND with OR"));
    MUTATIONS.put(Opcodes.IREM, new InsnSubstitution(Opcodes.IMUL,"AOR Mutator: Replaced integer modulus with multiplication"));
    MUTATIONS.put(Opcodes.IXOR, new InsnSubstitution(Opcodes.IAND,"AOR Mutator: Replaced XOR with AND"));
    MUTATIONS.put(Opcodes.ISHL, new InsnSubstitution(Opcodes.ISHR,"AOR Mutator: Replaced Shift Left with Shift Right"));
    MUTATIONS.put(Opcodes.ISHR, new InsnSubstitution(Opcodes.ISHL,"AOR Mutator: Replaced Shift Right with Shift Left"));
    MUTATIONS.put(Opcodes.IUSHR, new InsnSubstitution(Opcodes.ISHL,"AOR Mutator: Replaced Unsigned Shift Right with Shift Left"));

    // longs
    MUTATIONS.put(Opcodes.LADD, new InsnSubstitution(Opcodes.LSUB,"AOR Mutator: Replaced long addition with subtraction"));
    MUTATIONS.put(Opcodes.LSUB, new InsnSubstitution(Opcodes.LADD,"AOR Mutator: Replaced long subtraction with addition"));
    MUTATIONS.put(Opcodes.LMUL, new InsnSubstitution(Opcodes.LDIV,"AOR Mutator: Replaced long multiplication with division"));
    MUTATIONS.put(Opcodes.LDIV, new InsnSubstitution(Opcodes.LMUL,"AOR Mutator: Replaced long division with multiplication"));
    MUTATIONS.put(Opcodes.LOR, new InsnSubstitution(Opcodes.LAND,"AOR Mutator: Replaced bitwise OR with AND"));
    MUTATIONS.put(Opcodes.LAND, new InsnSubstitution(Opcodes.LOR,"AOR Mutator: Replaced bitwise AND with OR"));
    MUTATIONS.put(Opcodes.LREM, new InsnSubstitution(Opcodes.LMUL,"AOR Mutator: Replaced long modulus with multiplication"));
    MUTATIONS.put(Opcodes.LXOR, new InsnSubstitution(Opcodes.LAND,"AOR Mutator: Replaced XOR with AND"));
    MUTATIONS.put(Opcodes.LSHL, new InsnSubstitution(Opcodes.LSHR,"AOR Mutator: Replaced Shift Left with Shift Right"));
    MUTATIONS.put(Opcodes.LSHR, new InsnSubstitution(Opcodes.LSHL,"AOR Mutator: Replaced Shift Right with Shift Left"));
    MUTATIONS.put(Opcodes.LUSHR, new InsnSubstitution(Opcodes.LSHL,"AOR Mutator: Replaced Unsigned Shift Right with Shift Left"));

    // floats
    MUTATIONS.put(Opcodes.FADD, new InsnSubstitution(Opcodes.FSUB,"AOR Mutator: Replaced float addition with subtraction"));
    MUTATIONS.put(Opcodes.FSUB, new InsnSubstitution(Opcodes.FADD,"AOR Mutator: Replaced float subtraction with addition"));
    MUTATIONS.put(Opcodes.FMUL, new InsnSubstitution(Opcodes.FDIV,"AOR Mutator: Replaced float multiplication with division"));
    MUTATIONS.put(Opcodes.FDIV, new InsnSubstitution(Opcodes.FMUL,"AOR Mutator: Replaced float division with multiplication"));
    MUTATIONS.put(Opcodes.FREM, new InsnSubstitution(Opcodes.FMUL,"AOR Mutator: Replaced float modulus with multiplication"));

    // doubles
    MUTATIONS.put(Opcodes.DADD, new InsnSubstitution(Opcodes.DSUB,"AOR Mutator: Replaced double addition with subtraction"));
    MUTATIONS.put(Opcodes.DSUB, new InsnSubstitution(Opcodes.DADD,"AOR Mutator: Replaced double subtraction with addition"));
    MUTATIONS.put(Opcodes.DMUL, new InsnSubstitution(Opcodes.DDIV,"AOR Mutator: Replaced double multiplication with division"));
    MUTATIONS.put(Opcodes.DDIV, new InsnSubstitution(Opcodes.DMUL,"AOR Mutator: Replaced double division with multiplication"));
    MUTATIONS.put(Opcodes.DREM, new InsnSubstitution(Opcodes.DMUL,"AOR Mutator: Replaced double modulus with multiplication"));

  }

  @Override
  protected Map<Integer, ZeroOperandMutation> getMutations() {
    return MUTATIONS;
  }

}

class MathMethodVisitorC2 extends AbstractInsnMutator {

  MathMethodVisitorC2(final MethodMutatorFactory factory,
      final MethodInfo methodInfo, final MutationContext context,
      final MethodVisitor writer) {
    super(factory, methodInfo, context, writer);
  }

  private static final Map<Integer, ZeroOperandMutation> MUTATIONS = new HashMap<Integer, ZeroOperandMutation>();

  static {
    MUTATIONS.put(Opcodes.IADD, new InsnSubstitution(Opcodes.IMUL,"AOR Mutator: Replaced integer addition with multiplication"));
    MUTATIONS.put(Opcodes.ISUB, new InsnSubstitution(Opcodes.IDIV,"AOR Mutator: Replaced integer subtraction with division"));
    MUTATIONS.put(Opcodes.IMUL, new InsnSubstitution(Opcodes.IADD,"AOR Mutator: Replaced integer multiplication with addition"));
    MUTATIONS.put(Opcodes.IDIV, new InsnSubstitution(Opcodes.ISUB,"AOR Mutator: Replaced integer division with subtraction"));
    MUTATIONS.put(Opcodes.IREM, new InsnSubstitution(Opcodes.IDIV,"AOR Mutator: Replaced integer modulus with division"));

    // longs
    MUTATIONS.put(Opcodes.LADD, new InsnSubstitution(Opcodes.LMUL,"AOR Mutator: Replaced long addition with multiplication"));
    MUTATIONS.put(Opcodes.LSUB, new InsnSubstitution(Opcodes.LDIV,"AOR Mutator: Replaced long subtraction with division"));
    MUTATIONS.put(Opcodes.LMUL, new InsnSubstitution(Opcodes.LADD,"AOR Mutator: Replaced long multiplication with addition"));
    MUTATIONS.put(Opcodes.LDIV, new InsnSubstitution(Opcodes.LSUB,"AOR Mutator: Replaced long division with subtraction"));
    MUTATIONS.put(Opcodes.LREM, new InsnSubstitution(Opcodes.LDIV,"AOR Mutator: Replaced long modulus with division"));


    // floats
    MUTATIONS.put(Opcodes.FADD, new InsnSubstitution(Opcodes.FMUL,"AOR Mutator: Replaced float addition with multiplication"));
    MUTATIONS.put(Opcodes.FSUB, new InsnSubstitution(Opcodes.FDIV,"AOR Mutator: Replaced float subtraction with division"));
    MUTATIONS.put(Opcodes.FMUL, new InsnSubstitution(Opcodes.FSUB,"AOR Mutator: Replaced float multiplication with subtraction"));
    MUTATIONS.put(Opcodes.FDIV, new InsnSubstitution(Opcodes.FADD,"AOR Mutator: Replaced float division with addition"));
    MUTATIONS.put(Opcodes.FREM, new InsnSubstitution(Opcodes.FDIV,"AOR Mutator: Replaced float modulus with division"));

    // doubles
    MUTATIONS.put(Opcodes.DADD, new InsnSubstitution(Opcodes.DMUL,"AOR Mutator: Replaced double addition with multiplication"));
    MUTATIONS.put(Opcodes.DSUB, new InsnSubstitution(Opcodes.DDIV,"AOR Mutator: Replaced double subtraction with division"));
    MUTATIONS.put(Opcodes.DMUL, new InsnSubstitution(Opcodes.DADD,"AOR Mutator: Replaced double multiplication with addition"));
    MUTATIONS.put(Opcodes.DDIV, new InsnSubstitution(Opcodes.DSUB,"AOR Mutator: Replaced double division with subtraction"));
    MUTATIONS.put(Opcodes.DREM, new InsnSubstitution(Opcodes.DDIV,"AOR Mutator: Replaced double modulus with division"));

  }

  @Override
  protected Map<Integer, ZeroOperandMutation> getMutations() {
    return MUTATIONS;
  }

}

class MathMethodVisitorC3 extends AbstractInsnMutator {

  MathMethodVisitorC3(final MethodMutatorFactory factory,
      final MethodInfo methodInfo, final MutationContext context,
      final MethodVisitor writer) {
    super(factory, methodInfo, context, writer);
  }

  private static final Map<Integer, ZeroOperandMutation> MUTATIONS = new HashMap<Integer, ZeroOperandMutation>();

  static {
    MUTATIONS.put(Opcodes.IADD, new InsnSubstitution(Opcodes.IDIV,"AOR Mutator: Replaced integer addition with division"));
    MUTATIONS.put(Opcodes.ISUB, new InsnSubstitution(Opcodes.IMUL,"AOR Mutator: Replaced integer subtraction with multiplication"));
    MUTATIONS.put(Opcodes.IMUL, new InsnSubstitution(Opcodes.ISUB,"AOR Mutator: Replaced integer multiplication with subtraction"));
    MUTATIONS.put(Opcodes.IDIV, new InsnSubstitution(Opcodes.IADD,"AOR Mutator: Replaced integer division with addition"));
    MUTATIONS.put(Opcodes.IREM, new InsnSubstitution(Opcodes.IADD,"AOR Mutator: Replaced integer modulus with addition"));

    // longs
    MUTATIONS.put(Opcodes.LADD, new InsnSubstitution(Opcodes.LDIV,"AOR Mutator: Replaced long addition with division"));
    MUTATIONS.put(Opcodes.LSUB, new InsnSubstitution(Opcodes.LMUL,"AOR Mutator: Replaced long subtraction with multiplication"));
    MUTATIONS.put(Opcodes.LMUL, new InsnSubstitution(Opcodes.LSUB,"AOR Mutator: Replaced long multiplication with subtraction"));
    MUTATIONS.put(Opcodes.LDIV, new InsnSubstitution(Opcodes.LADD,"AOR Mutator: Replaced long division with addition"));
    MUTATIONS.put(Opcodes.LREM, new InsnSubstitution(Opcodes.LADD,"AOR Mutator: Replaced long modulus with addition"));

    // floats
    MUTATIONS.put(Opcodes.FADD, new InsnSubstitution(Opcodes.FDIV,"AOR Mutator: Replaced float addition with division"));
    MUTATIONS.put(Opcodes.FSUB, new InsnSubstitution(Opcodes.FMUL,"AOR Mutator: Replaced float subtraction with multiplication"));
    MUTATIONS.put(Opcodes.FMUL, new InsnSubstitution(Opcodes.FADD,"AOR Mutator: Replaced float multiplication with addition"));
    MUTATIONS.put(Opcodes.FDIV, new InsnSubstitution(Opcodes.FSUB,"AOR Mutator: Replaced float division with subtraction"));
    MUTATIONS.put(Opcodes.FREM, new InsnSubstitution(Opcodes.FADD,"AOR Mutator: Replaced float modulus with addition"));

    // doubles
    MUTATIONS.put(Opcodes.DADD, new InsnSubstitution(Opcodes.DDIV,"AOR Mutator: Replaced double addition with division"));
    MUTATIONS.put(Opcodes.DSUB, new InsnSubstitution(Opcodes.DMUL,"AOR Mutator: Replaced double subtraction with multiplication"));
    MUTATIONS.put(Opcodes.DMUL, new InsnSubstitution(Opcodes.DSUB,"AOR Mutator: Replaced double multiplication with subtraction"));
    MUTATIONS.put(Opcodes.DDIV, new InsnSubstitution(Opcodes.DADD,"AOR Mutator: Replaced double division with addition"));
    MUTATIONS.put(Opcodes.DREM, new InsnSubstitution(Opcodes.DADD,"AOR Mutator: Replaced double modulus with addition"));

  }

  @Override
  protected Map<Integer, ZeroOperandMutation> getMutations() {
    return MUTATIONS;
  }

}

class MathMethodVisitorC4 extends AbstractInsnMutator {

  MathMethodVisitorC4(final MethodMutatorFactory factory,
      final MethodInfo methodInfo, final MutationContext context,
      final MethodVisitor writer) {
    super(factory, methodInfo, context, writer);
  }

  private static final Map<Integer, ZeroOperandMutation> MUTATIONS = new HashMap<Integer, ZeroOperandMutation>();

  static {
    MUTATIONS.put(Opcodes.IADD, new InsnSubstitution(Opcodes.IREM,"AOR Mutator: Replaced integer addition with modulus"));
    MUTATIONS.put(Opcodes.ISUB, new InsnSubstitution(Opcodes.IREM,"AOR Mutator: Replaced integer subtraction with modulus"));
    MUTATIONS.put(Opcodes.IMUL, new InsnSubstitution(Opcodes.IREM,"AOR Mutator: Replaced integer multiplication with modulus"));
    MUTATIONS.put(Opcodes.IDIV, new InsnSubstitution(Opcodes.IREM,"AOR Mutator: Replaced integer division with modulus"));
    MUTATIONS.put(Opcodes.IREM, new InsnSubstitution(Opcodes.ISUB,"AOR Mutator: Replaced integer modulus with subtraction"));

    // longs
    MUTATIONS.put(Opcodes.LADD, new InsnSubstitution(Opcodes.LREM,"AOR Mutator: Replaced long addition with modulus"));
    MUTATIONS.put(Opcodes.LSUB, new InsnSubstitution(Opcodes.LREM,"AOR Mutator: Replaced long subtraction with modulus"));
    MUTATIONS.put(Opcodes.LMUL, new InsnSubstitution(Opcodes.LREM,"AOR Mutator: Replaced long multiplication with modulus"));
    MUTATIONS.put(Opcodes.LDIV, new InsnSubstitution(Opcodes.LREM,"AOR Mutator: Replaced long division with modulus"));
    MUTATIONS.put(Opcodes.LREM, new InsnSubstitution(Opcodes.LSUB,"AOR Mutator: Replaced long modulus with subtraction"));

    // floats
    MUTATIONS.put(Opcodes.FADD, new InsnSubstitution(Opcodes.FREM,"AOR Mutator: Replaced float addition with modulus"));
    MUTATIONS.put(Opcodes.FSUB, new InsnSubstitution(Opcodes.FREM,"AOR Mutator: Replaced float subtraction with modulus"));
    MUTATIONS.put(Opcodes.FMUL, new InsnSubstitution(Opcodes.FREM,"AOR Mutator: Replaced float multiplication with modulus"));
    MUTATIONS.put(Opcodes.FDIV, new InsnSubstitution(Opcodes.FREM,"AOR Mutator: Replaced float division with modulus"));
    MUTATIONS.put(Opcodes.FREM, new InsnSubstitution(Opcodes.FSUB,"AOR Mutator: Replaced float modulus with subtraction"));

    // doubles
    MUTATIONS.put(Opcodes.DADD, new InsnSubstitution(Opcodes.DREM,"AOR Mutator: Replaced double addition with modulus"));
    MUTATIONS.put(Opcodes.DSUB, new InsnSubstitution(Opcodes.DREM,"AOR Mutator: Replaced double subtraction with modulus"));
    MUTATIONS.put(Opcodes.DMUL, new InsnSubstitution(Opcodes.DREM,"AOR Mutator: Replaced double multiplication with modulus"));
    MUTATIONS.put(Opcodes.DDIV, new InsnSubstitution(Opcodes.DREM,"AOR Mutator: Replaced double division with modulus"));
    MUTATIONS.put(Opcodes.DREM, new InsnSubstitution(Opcodes.DSUB,"AOR Mutator: Replaced double modulus with subtraction"));
  }

  @Override
  protected Map<Integer, ZeroOperandMutation> getMutations() {
    return MUTATIONS;
  }
}