package org.pitest.mutationtest.engine.gregor.mutators;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.MutationIdentifier;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;

public class UORMutator implements MethodMutatorFactory {

    public enum Type {
        DECREMENT, INCREMENT, REVERSE, REMOVE
    }

    private final Type mutatorType;

    public UORMutator(Type t) {
        this.mutatorType = t;
    }

    @Override
    public MethodVisitor create(final MutationContext context, final MethodInfo methodInfo,
            final MethodVisitor methodVisitor) {
        if (this.mutatorType == UORMutator.Type.DECREMENT) {
            return new UorMethodVisitorDecrement(this, context, methodVisitor);
        } else if (this.mutatorType == UORMutator.Type.INCREMENT) {
            return new UorMethodVisitorIncrement(this, context, methodVisitor);
        } else if (this.mutatorType == UORMutator.Type.REMOVE) {
            return new UorMethodVisitorRemove(this, context, methodVisitor);
        } else if (this.mutatorType == UORMutator.Type.REVERSE) {
            return new UorMethodVisitorReverse(this, context, methodVisitor);
        } else {
            return null;
        }
    }

    @Override
    public String getGloballyUniqueId() {
        return this.getClass().getName() + "-" + this.mutatorType.name();
    }

    @Override
    public String getName() {
        return "UOR Mutator: " + this.mutatorType.name();
    }
}

class UorMethodVisitorDecrement extends MethodVisitor {

    private final MethodMutatorFactory factory;
    private final MutationContext context;

    UorMethodVisitorDecrement(final MethodMutatorFactory factory, final MutationContext context,
            final MethodVisitor delegateMethodVisitor) {
        super(Opcodes.ASM6, delegateMethodVisitor);
        this.factory = factory;
        this.context = context;
    }

    @Override
    public void visitIincInsn(final int var, final int increment) {
        String operator;
        if (increment > 0) {
            operator = "++";
        } else {
            operator = "--";
        }
        
        final MutationIdentifier newId = this.context.registerMutation(this.factory,
                "UOR Mutator: Decremented operator " + operator);
        if (this.context.shouldMutate(newId)) {
            this.mv.visitIincInsn(var, increment - 1);
        } else {
            this.mv.visitIincInsn(var, increment);
        }
    }
}

class UorMethodVisitorIncrement extends MethodVisitor {

    private final MethodMutatorFactory factory;
    private final MutationContext context;

    UorMethodVisitorIncrement(final MethodMutatorFactory factory, final MutationContext context,
            final MethodVisitor delegateMethodVisitor) {
        super(Opcodes.ASM6, delegateMethodVisitor);
        this.factory = factory;
        this.context = context;
    }

    @Override
    public void visitIincInsn(final int var, final int increment) {
        String operator;
        if (increment > 0) {
            operator = "++";
        } else {
            operator = "--";
        }
        
        final MutationIdentifier newId = this.context.registerMutation(this.factory,
                "UOR Mutator: Incremented operator " + operator);
        if (this.context.shouldMutate(newId)) {
            this.mv.visitIincInsn(var, increment + 1);
        } else {
            this.mv.visitIincInsn(var, increment);
        }
    }
}

class UorMethodVisitorReverse extends MethodVisitor {

    private final MethodMutatorFactory factory;
    private final MutationContext context;

    UorMethodVisitorReverse(final MethodMutatorFactory factory, final MutationContext context,
            final MethodVisitor delegateMethodVisitor) {
        super(Opcodes.ASM6, delegateMethodVisitor);
        this.factory = factory;
        this.context = context;
    }

    @Override
    public void visitIincInsn(final int var, final int increment) {
        String operator;
        String operator2;
        if (increment > 0) {
            operator = "++";
            operator2 = "--";
        } else {
            operator = "--";
            operator2 = "++";
        }
        
        final MutationIdentifier newId = this.context.registerMutation(this.factory,
                "UOR Mutator: Changed operator from " + operator + " to " + operator2);
        if (this.context.shouldMutate(newId)) {
            this.mv.visitIincInsn(var, -increment);
        } else {
            this.mv.visitIincInsn(var, increment);
        }
    }
}

class UorMethodVisitorRemove extends MethodVisitor {

    private final MethodMutatorFactory factory;
    private final MutationContext context;

    UorMethodVisitorRemove(final MethodMutatorFactory factory, final MutationContext context,
            final MethodVisitor delegateMethodVisitor) {
        super(Opcodes.ASM6, delegateMethodVisitor);
        this.factory = factory;
        this.context = context;
    }

    @Override
    public void visitIincInsn(final int var, final int increment) {
        String operator;
        if (increment > 0) {
            operator = "++";
        } else {
            operator = "--";
        }
        final MutationIdentifier newId = this.context.registerMutation(this.factory,
                "UOR Mutator: Removed operator: " + operator);
        if (this.context.shouldMutate(newId)) {
            this.mv.visitIincInsn(var, 0);
        } else {
            this.mv.visitIincInsn(var, increment);
        }
    }
}