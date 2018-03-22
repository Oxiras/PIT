package org.pitest.mutationtest.engine.gregor.mutators;

import java.util.HashMap;
import java.util.Map;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.gregor.AbstractJumpMutator;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;

public class RORMutator {
    public enum RORMutator1 implements MethodMutatorFactory {

        ROR_MUTATOR;

        @Override
        public MethodVisitor create(final MutationContext context, final MethodInfo methodInfo,
                final MethodVisitor methodVisitor) {
            return new RorMethodVisitor1(this, context, methodVisitor);
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
    
    public enum RORMutator2 implements MethodMutatorFactory {

        ROR_MUTATOR;

        @Override
        public MethodVisitor create(final MutationContext context, final MethodInfo methodInfo,
                final MethodVisitor methodVisitor) {
            return new RorMethodVisitor2(this, context, methodVisitor);
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
    
    public enum RORMutator3 implements MethodMutatorFactory {

        ROR_MUTATOR;

        @Override
        public MethodVisitor create(final MutationContext context, final MethodInfo methodInfo,
                final MethodVisitor methodVisitor) {
            return new RorMethodVisitor3(this, context, methodVisitor);
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
    
    public enum RORMutator4 implements MethodMutatorFactory {

        ROR_MUTATOR;

        @Override
        public MethodVisitor create(final MutationContext context, final MethodInfo methodInfo,
                final MethodVisitor methodVisitor) {
            return new RorMethodVisitor4(this, context, methodVisitor);
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
    
    public enum RORMutator5 implements MethodMutatorFactory {

        ROR_MUTATOR;

        @Override
        public MethodVisitor create(final MutationContext context, final MethodInfo methodInfo,
                final MethodVisitor methodVisitor) {
            return new RorMethodVisitor5(this, context, methodVisitor);
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
    
    public enum RORMutator6 implements MethodMutatorFactory {

        ROR_MUTATOR;

        @Override
        public MethodVisitor create(final MutationContext context, final MethodInfo methodInfo,
                final MethodVisitor methodVisitor) {
            return new RorMethodVisitor6(this, context, methodVisitor);
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

class RorMethodVisitor1 extends AbstractJumpMutator {

    private static final Map<Integer, Substitution> MUTATIONS = new HashMap<>();

    static {
        // Equal with Not Equal
        MUTATIONS.put(Opcodes.IF_ICMPEQ, new Substitution(Opcodes.IF_ICMPNE, "ROR Mutator: Replaced '!=' with '=='."));

        // Greater Than or Equal with Equal
        MUTATIONS.put(Opcodes.IF_ICMPGE, new Substitution(Opcodes.IF_ICMPNE, "ROR Mutator: Replaced '<' with '=='."));

        // Greater Than with Equal
        MUTATIONS.put(Opcodes.IF_ICMPGT, new Substitution(Opcodes.IF_ICMPNE, "ROR Mutator: Replaced '<=' with '=='."));

        // Less Than or Equal with Equal
        MUTATIONS.put(Opcodes.IF_ICMPLE, new Substitution(Opcodes.IF_ICMPNE, "ROR Mutator: Replaced '>' with '=='."));

        // Less Than with Equal
        MUTATIONS.put(Opcodes.IF_ICMPLT, new Substitution(Opcodes.IF_ICMPNE, "ROR Mutator: Replaced '>=' with '=='."));
    }

    RorMethodVisitor1(final MethodMutatorFactory factory, final MutationContext context,
            final MethodVisitor delegateMethodVisitor) {
        super(factory, context, delegateMethodVisitor);
    }

    @Override
    protected Map<Integer, Substitution> getMutations() {
        return MUTATIONS;
    }
}

class RorMethodVisitor2 extends AbstractJumpMutator {

    private static final Map<Integer, Substitution> MUTATIONS = new HashMap<>();

    static {
        // Not Equal with Greater Than or Equal
        MUTATIONS.put(Opcodes.IF_ICMPNE, new Substitution(Opcodes.IF_ICMPEQ, "ROR Mutator: Replaced '==' with '!='."));

        // Greater Than or Equal with Not Equal
        MUTATIONS.put(Opcodes.IF_ICMPGE, new Substitution(Opcodes.IF_ICMPEQ, "ROR Mutator: Replaced '<' with '!='."));

        // Greater Than with Not Equal
        MUTATIONS.put(Opcodes.IF_ICMPGT, new Substitution(Opcodes.IF_ICMPEQ, "ROR Mutator: Replaced '<=' with '!='."));

        // Less Than or Equal with Not Equal
        MUTATIONS.put(Opcodes.IF_ICMPLE, new Substitution(Opcodes.IF_ICMPEQ, "ROR Mutator: Replaced '>' with '!='."));

        // Less Than with Not Equal
        MUTATIONS.put(Opcodes.IF_ICMPLT, new Substitution(Opcodes.IF_ICMPEQ, "ROR Mutator: Replaced '>=' with '!='."));
    }

    @Override
    protected Map<Integer, Substitution> getMutations() {
        return MUTATIONS;
    }

    RorMethodVisitor2(final MethodMutatorFactory factory, final MutationContext context,
            final MethodVisitor delegateMethodVisitor) {
        super(factory, context, delegateMethodVisitor);
    }
}

class RorMethodVisitor3 extends AbstractJumpMutator {

    private static final Map<Integer, Substitution> MUTATIONS = new HashMap<>();

    static {
        // Equal with Greater Than
        MUTATIONS.put(Opcodes.IF_ICMPEQ, new Substitution(Opcodes.IF_ICMPGE, "ROR Mutator: Replaced '!=' with '<'."));

        // Not Equal with Greater Than
        MUTATIONS.put(Opcodes.IF_ICMPNE, new Substitution(Opcodes.IF_ICMPGE, "ROR Mutator: Replaced '==' with '<'."));

        // Greater Than with Greater Than or Equal
        MUTATIONS.put(Opcodes.IF_ICMPGT, new Substitution(Opcodes.IF_ICMPGE, "ROR Mutator: Replaced '<=' with '<'."));

        // Less Than or Equal with Greater Than or Equal
        MUTATIONS.put(Opcodes.IF_ICMPLE, new Substitution(Opcodes.IF_ICMPGE, "ROR Mutator: Replaced '>' with '<'."));

        // Less Than with Greater Than or Equal
        MUTATIONS.put(Opcodes.IF_ICMPLT, new Substitution(Opcodes.IF_ICMPGE, "ROR Mutator: Replaced '>=' with '<'."));
    }

    RorMethodVisitor3(final MethodMutatorFactory factory, final MutationContext context,
            final MethodVisitor delegateMethodVisitor) {
        super(factory, context, delegateMethodVisitor);
    }

    @Override
    protected Map<Integer, Substitution> getMutations() {
        return MUTATIONS;
    }
}

class RorMethodVisitor4 extends AbstractJumpMutator {

    private static final Map<Integer, Substitution> MUTATIONS = new HashMap<>();

    static {
        // Equal with Less Than or Equal
        MUTATIONS.put(Opcodes.IF_ICMPEQ, new Substitution(Opcodes.IF_ICMPGT, "ROR Mutator: Replaced '!=' with '<='."));

        // Not Equal with Less Than or Equal
        MUTATIONS.put(Opcodes.IF_ICMPNE, new Substitution(Opcodes.IF_ICMPGT, "ROR Mutator: Replaced '==' with '<='."));

        // Greater Than or Equal with Less Than or Equal
        MUTATIONS.put(Opcodes.IF_ICMPGE, new Substitution(Opcodes.IF_ICMPGT, "ROR Mutator: Replaced '<' with '<='."));

        // Greater Than with Less Than or Equal
        MUTATIONS.put(Opcodes.IF_ICMPLE, new Substitution(Opcodes.IF_ICMPGT, "ROR Mutator: Replaced '>' with '<='."));

        // Less Than with Greater Than
        MUTATIONS.put(Opcodes.IF_ICMPLT, new Substitution(Opcodes.IF_ICMPGT, "ROR Mutator: Replaced '>=' with '<='."));
    }

    RorMethodVisitor4(final MethodMutatorFactory factory, final MutationContext context,
            final MethodVisitor delegateMethodVisitor) {
        super(factory, context, delegateMethodVisitor);
    }

    @Override
    protected Map<Integer, Substitution> getMutations() {
        return MUTATIONS;
    }
}

class RorMethodVisitor5 extends AbstractJumpMutator {

    private static final Map<Integer, Substitution> MUTATIONS = new HashMap<>();

    static {
        // Equal with Less Than or Equal
        MUTATIONS.put(Opcodes.IF_ICMPEQ, new Substitution(Opcodes.IF_ICMPLE, "ROR Mutator: Replaced '!=' with '>'."));

        // Not Equal with Less Than or Equal
        MUTATIONS.put(Opcodes.IF_ICMPNE, new Substitution(Opcodes.IF_ICMPLE, "ROR Mutator: Replaced '==' with '>'."));

        // Greater Than or Equal with Less Than or Equal
        MUTATIONS.put(Opcodes.IF_ICMPGE, new Substitution(Opcodes.IF_ICMPLE, "ROR Mutator: Replaced '<' with '>'."));

        // Greater Than with Less Than or Equal
        MUTATIONS.put(Opcodes.IF_ICMPGT, new Substitution(Opcodes.IF_ICMPLE, "ROR Mutator: Replaced '<=' with '>'."));

        // Less Than with Greater Than
        MUTATIONS.put(Opcodes.IF_ICMPLT, new Substitution(Opcodes.IF_ICMPLE, "ROR Mutator: Replaced '>=' with '>'."));
    }

    RorMethodVisitor5(final MethodMutatorFactory factory, final MutationContext context,
            final MethodVisitor delegateMethodVisitor) {
        super(factory, context, delegateMethodVisitor);
    }

    @Override
    protected Map<Integer, Substitution> getMutations() {
        return MUTATIONS;
    }
}

class RorMethodVisitor6 extends AbstractJumpMutator {

    private static final Map<Integer, Substitution> MUTATIONS = new HashMap<>();

    static {
        // Equal with Less Than
        MUTATIONS.put(Opcodes.IF_ICMPEQ, new Substitution(Opcodes.IF_ICMPLT, "ROR Mutator: Replaced '!=' with '>='."));

        // Not Equal with Less Than
        MUTATIONS.put(Opcodes.IF_ICMPNE, new Substitution(Opcodes.IF_ICMPLT, "ROR Mutator: Replaced '==' with '>='."));

        // Greater Than or Equal with Less Than
        MUTATIONS.put(Opcodes.IF_ICMPGE, new Substitution(Opcodes.IF_ICMPLT, "ROR Mutator: Replaced '<' with '>='."));

        // Greater Than with Less Than
        MUTATIONS.put(Opcodes.IF_ICMPGT, new Substitution(Opcodes.IF_ICMPLT, "ROR Mutator: Replaced '<=' with '>='."));

        // Less Than or Equal with Less Than
        MUTATIONS.put(Opcodes.IF_ICMPLE, new Substitution(Opcodes.IF_ICMPLT, "ROR Mutator: Replaced '>' with '>='."));
    }

    RorMethodVisitor6(final MethodMutatorFactory factory, final MutationContext context,
            final MethodVisitor delegateMethodVisitor) {
        super(factory, context, delegateMethodVisitor);
    }

    @Override
    protected Map<Integer, Substitution> getMutations() {
        return MUTATIONS;
    }
}