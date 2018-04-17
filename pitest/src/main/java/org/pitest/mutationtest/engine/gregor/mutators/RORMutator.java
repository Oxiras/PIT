package org.pitest.mutationtest.engine.gregor.mutators;

import java.util.HashMap;
import java.util.Map;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.gregor.AbstractJumpMutator;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;

public class RORMutator implements MethodMutatorFactory {
    
    public enum Type {
        NE, EQ, GE, GT, LE, LT
    }
    
    private final Type mutatorType;
    
    public RORMutator(Type t) {
        this.mutatorType = t;
    }
    
    @Override
    public MethodVisitor create(final MutationContext context,
            final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
        if (this.mutatorType == RORMutator.Type.NE) {
            return new RorMethodVisitorNE(this, context, methodVisitor);
        } else if (this.mutatorType == RORMutator.Type.EQ) {
            return new RorMethodVisitorEQ(this, context, methodVisitor);
        } else if (this.mutatorType == RORMutator.Type.GE) {
            return new RorMethodVisitorGE(this, context, methodVisitor);
        } else if (this.mutatorType == RORMutator.Type.GT) {
            return new RorMethodVisitorGT(this, context, methodVisitor);
        } else if (this.mutatorType == RORMutator.Type.LE) {
            return new RorMethodVisitorLE(this, context, methodVisitor);
        } else if (this.mutatorType == RORMutator.Type.LT) {
            return new RorMethodVisitorLT(this, context, methodVisitor);
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
        return "ROR Mutator: " + this.mutatorType.name();
    }   
}

class RorMethodVisitorNE extends AbstractJumpMutator {

    private static final Map<Integer, Substitution> MUTATIONS = new HashMap<>();

    static {     
        // Not Equal with Equal
        MUTATIONS.put(Opcodes.IF_ICMPEQ, new Substitution(Opcodes.IF_ICMPNE, "ROR Mutator: Replaced '!=' with '=='."));
        MUTATIONS.put(Opcodes.IFEQ, new Substitution(Opcodes.IFNE, "ROR Mutator: Replaced '!=' with '=='."));

        // Less Than with Equal
        MUTATIONS.put(Opcodes.IF_ICMPGE, new Substitution(Opcodes.IF_ICMPNE, "ROR Mutator: Replaced '<' with '=='."));
        MUTATIONS.put(Opcodes.IFGE, new Substitution(Opcodes.IFNE, "ROR Mutator: Replaced '<' with '=='."));

        // Less Than or Equal with Equal
        MUTATIONS.put(Opcodes.IF_ICMPGT, new Substitution(Opcodes.IF_ICMPNE, "ROR Mutator: Replaced '<=' with '=='."));
        MUTATIONS.put(Opcodes.IFGT, new Substitution(Opcodes.IFNE, "ROR Mutator: Replaced '<=' with '=='."));

        // Greater Than with Equal
        MUTATIONS.put(Opcodes.IF_ICMPLE, new Substitution(Opcodes.IF_ICMPNE, "ROR Mutator: Replaced '>' with '=='."));
        MUTATIONS.put(Opcodes.IFLE, new Substitution(Opcodes.IFNE, "ROR Mutator: Replaced '>' with '=='."));

        // Greater Than or Equal with Equal
        MUTATIONS.put(Opcodes.IF_ICMPLT, new Substitution(Opcodes.IF_ICMPNE, "ROR Mutator: Replaced '>=' with '=='."));
        MUTATIONS.put(Opcodes.IFLT, new Substitution(Opcodes.IFNE, "ROR Mutator: Replaced '>=' with '=='."));
    }

    RorMethodVisitorNE(final MethodMutatorFactory factory, final MutationContext context,
            final MethodVisitor delegateMethodVisitor) {
        super(factory, context, delegateMethodVisitor);
    }

    @Override
    protected Map<Integer, Substitution> getMutations() {
        return MUTATIONS;
    }
}

class RorMethodVisitorEQ extends AbstractJumpMutator {

    private static final Map<Integer, Substitution> MUTATIONS = new HashMap<>();

    static {
        // Equal with Not Equal
        MUTATIONS.put(Opcodes.IF_ICMPNE, new Substitution(Opcodes.IF_ICMPEQ, "ROR Mutator: Replaced '==' with '!='."));
        MUTATIONS.put(Opcodes.IFNE, new Substitution(Opcodes.IFEQ, "ROR Mutator: Replaced '==' with '!='."));
        
        // Less Than with Not Equal
        MUTATIONS.put(Opcodes.IF_ICMPGE, new Substitution(Opcodes.IF_ICMPEQ, "ROR Mutator: Replaced '<' with '!='."));
        MUTATIONS.put(Opcodes.IFGE, new Substitution(Opcodes.IFEQ, "ROR Mutator: Replaced '<' with '!='."));
        
        // Less Than or Equal with Not Equal
        MUTATIONS.put(Opcodes.IF_ICMPGT, new Substitution(Opcodes.IF_ICMPEQ, "ROR Mutator: Replaced '<=' with '!='."));
        MUTATIONS.put(Opcodes.IFGT, new Substitution(Opcodes.IFEQ, "ROR Mutator: Replaced '<=' with '!='."));
        
        // Greater Than with Not Equal
        MUTATIONS.put(Opcodes.IF_ICMPLE, new Substitution(Opcodes.IF_ICMPEQ, "ROR Mutator: Replaced '>' with '!='."));
        MUTATIONS.put(Opcodes.IFLE, new Substitution(Opcodes.IFEQ, "ROR Mutator: Replaced '>' with '!='."));
        
        // Greater Than or Equal with Not Equal
        MUTATIONS.put(Opcodes.IF_ICMPLT, new Substitution(Opcodes.IF_ICMPEQ, "ROR Mutator: Replaced '>=' with '!='."));
        MUTATIONS.put(Opcodes.IFLT, new Substitution(Opcodes.IFEQ, "ROR Mutator: Replaced '>=' with '!='."));
    }

    @Override
    protected Map<Integer, Substitution> getMutations() {
        return MUTATIONS;
    }

    RorMethodVisitorEQ(final MethodMutatorFactory factory, final MutationContext context,
            final MethodVisitor delegateMethodVisitor) {
        super(factory, context, delegateMethodVisitor);
    }
}

class RorMethodVisitorGE extends AbstractJumpMutator {

    private static final Map<Integer, Substitution> MUTATIONS = new HashMap<>();

    static {
        // Not Equal with Less Than
        MUTATIONS.put(Opcodes.IF_ICMPEQ, new Substitution(Opcodes.IF_ICMPGE, "ROR Mutator: Replaced '!=' with '<'."));
        MUTATIONS.put(Opcodes.IFEQ, new Substitution(Opcodes.IFGE, "ROR Mutator: Replaced '!=' with '<'."));
        
        // Equal with Less Than
        MUTATIONS.put(Opcodes.IF_ICMPNE, new Substitution(Opcodes.IF_ICMPGE, "ROR Mutator: Replaced '==' with '<'."));
        MUTATIONS.put(Opcodes.IFNE, new Substitution(Opcodes.IFGE, "ROR Mutator: Replaced '==' with '<'."));
        
        // Less Than or Equal with Less Than
        MUTATIONS.put(Opcodes.IF_ICMPGT, new Substitution(Opcodes.IF_ICMPGE, "ROR Mutator: Replaced '<=' with '<'."));
        MUTATIONS.put(Opcodes.IFGT, new Substitution(Opcodes.IFGE, "ROR Mutator: Replaced '<=' with '<'."));
        
        // Greater Than with Less Than
        MUTATIONS.put(Opcodes.IF_ICMPLE, new Substitution(Opcodes.IF_ICMPGE, "ROR Mutator: Replaced '>' with '<'."));
        MUTATIONS.put(Opcodes.IFLE, new Substitution(Opcodes.IFGE, "ROR Mutator: Replaced '>' with '<'."));
        
        // Greater Than or Equal with Less Than
        MUTATIONS.put(Opcodes.IF_ICMPLT, new Substitution(Opcodes.IF_ICMPGE, "ROR Mutator: Replaced '>=' with '<'."));
        MUTATIONS.put(Opcodes.IFLT, new Substitution(Opcodes.IFGE, "ROR Mutator: Replaced '>=' with '<'."));
    }

    RorMethodVisitorGE(final MethodMutatorFactory factory, final MutationContext context,
            final MethodVisitor delegateMethodVisitor) {
        super(factory, context, delegateMethodVisitor);
    }

    @Override
    protected Map<Integer, Substitution> getMutations() {
        return MUTATIONS;
    }
}

class RorMethodVisitorGT extends AbstractJumpMutator {

    private static final Map<Integer, Substitution> MUTATIONS = new HashMap<>();

    static {
        // Not Equal with Less Than or Equal
        MUTATIONS.put(Opcodes.IF_ICMPEQ, new Substitution(Opcodes.IF_ICMPGT, "ROR Mutator: Replaced '!=' with '<='."));
        MUTATIONS.put(Opcodes.IFEQ, new Substitution(Opcodes.IFGT, "ROR Mutator: Replaced '!=' with '<='."));
        
        // Equal with Less Than or Equal
        MUTATIONS.put(Opcodes.IF_ICMPNE, new Substitution(Opcodes.IF_ICMPGT, "ROR Mutator: Replaced '==' with '<='."));
        MUTATIONS.put(Opcodes.IFNE, new Substitution(Opcodes.IFGT, "ROR Mutator: Replaced '==' with '<='."));
        
        // Less Than with Less Than or Equal
        MUTATIONS.put(Opcodes.IF_ICMPGE, new Substitution(Opcodes.IF_ICMPGT, "ROR Mutator: Replaced '<' with '<='."));
        MUTATIONS.put(Opcodes.IFGE, new Substitution(Opcodes.IFGT, "ROR Mutator: Replaced '<' with '<='."));
        
        // Greater Than with Less Than or Equal
        MUTATIONS.put(Opcodes.IF_ICMPLE, new Substitution(Opcodes.IF_ICMPGT, "ROR Mutator: Replaced '>' with '<='."));
        MUTATIONS.put(Opcodes.IFLE, new Substitution(Opcodes.IFGT, "ROR Mutator: Replaced '>' with '<='."));
        
        // Greater Than or Equal with Less Than or Equal
        MUTATIONS.put(Opcodes.IF_ICMPLT, new Substitution(Opcodes.IF_ICMPGT, "ROR Mutator: Replaced '>=' with '<='."));
        MUTATIONS.put(Opcodes.IFLT, new Substitution(Opcodes.IFGT, "ROR Mutator: Replaced '>=' with '<='."));
    }

    RorMethodVisitorGT(final MethodMutatorFactory factory, final MutationContext context,
            final MethodVisitor delegateMethodVisitor) {
        super(factory, context, delegateMethodVisitor);
    }

    @Override
    protected Map<Integer, Substitution> getMutations() {
        return MUTATIONS;
    }
}

class RorMethodVisitorLE extends AbstractJumpMutator {

    private static final Map<Integer, Substitution> MUTATIONS = new HashMap<>();

    static {
        // Not Equal with Greater Than
        MUTATIONS.put(Opcodes.IF_ICMPEQ, new Substitution(Opcodes.IF_ICMPLE, "ROR Mutator: Replaced '!=' with '>'."));
        MUTATIONS.put(Opcodes.IFEQ, new Substitution(Opcodes.IFLE, "ROR Mutator: Replaced '!=' with '>'."));
        
        // Equal with Greater Than
        MUTATIONS.put(Opcodes.IF_ICMPNE, new Substitution(Opcodes.IF_ICMPLE, "ROR Mutator: Replaced '==' with '>'."));
        MUTATIONS.put(Opcodes.IFNE, new Substitution(Opcodes.IFLE, "ROR Mutator: Replaced '==' with '>'."));
        
        // Less Than with Greater Than
        MUTATIONS.put(Opcodes.IF_ICMPGE, new Substitution(Opcodes.IF_ICMPLE, "ROR Mutator: Replaced '<' with '>'."));
        MUTATIONS.put(Opcodes.IFGE, new Substitution(Opcodes.IFLE, "ROR Mutator: Replaced '<' with '>'."));
        
        // Less Than or Equal with Greater Than
        MUTATIONS.put(Opcodes.IF_ICMPGT, new Substitution(Opcodes.IF_ICMPLE, "ROR Mutator: Replaced '<=' with '>'."));
        MUTATIONS.put(Opcodes.IFGT, new Substitution(Opcodes.IFLE, "ROR Mutator: Replaced '<=' with '>'."));
        
        // Greater Than or Equal with Greater Than
        MUTATIONS.put(Opcodes.IF_ICMPLT, new Substitution(Opcodes.IF_ICMPLE, "ROR Mutator: Replaced '>=' with '>'."));
        MUTATIONS.put(Opcodes.IFLT, new Substitution(Opcodes.IFLE, "ROR Mutator: Replaced '>=' with '>'."));
    }

    RorMethodVisitorLE(final MethodMutatorFactory factory, final MutationContext context,
            final MethodVisitor delegateMethodVisitor) {
        super(factory, context, delegateMethodVisitor);
    }

    @Override
    protected Map<Integer, Substitution> getMutations() {
        return MUTATIONS;
    }
}

class RorMethodVisitorLT extends AbstractJumpMutator {

    private static final Map<Integer, Substitution> MUTATIONS = new HashMap<>();

    static {
        // Not Equal with Greater Than or Equal
        MUTATIONS.put(Opcodes.IF_ICMPEQ, new Substitution(Opcodes.IF_ICMPLT, "ROR Mutator: Replaced '!=' with '>='."));
        MUTATIONS.put(Opcodes.IFEQ, new Substitution(Opcodes.IFLT, "ROR Mutator: Replaced '!=' with '>='."));
        
        // Equal with Greater Than or Equal
        MUTATIONS.put(Opcodes.IF_ICMPNE, new Substitution(Opcodes.IF_ICMPLT, "ROR Mutator: Replaced '==' with '>='."));
        MUTATIONS.put(Opcodes.IFNE, new Substitution(Opcodes.IFLT, "ROR Mutator: Replaced '==' with '>='."));
        
        // Less Than with Greater Than or Equal
        MUTATIONS.put(Opcodes.IF_ICMPGE, new Substitution(Opcodes.IF_ICMPLT, "ROR Mutator: Replaced '<' with '>='."));
        MUTATIONS.put(Opcodes.IFGE, new Substitution(Opcodes.IFLT, "ROR Mutator: Replaced '<' with '>='."));
        
        // Less Than or Equal with Greater Than or Equal
        MUTATIONS.put(Opcodes.IF_ICMPGT, new Substitution(Opcodes.IF_ICMPLT, "ROR Mutator: Replaced '<=' with '>='."));
        MUTATIONS.put(Opcodes.IFGT, new Substitution(Opcodes.IFLT, "ROR Mutator: Replaced '<=' with '>='."));
        
        // Greater Than with Greater Than or Equal
        MUTATIONS.put(Opcodes.IF_ICMPLE, new Substitution(Opcodes.IF_ICMPLT, "ROR Mutator: Replaced '>' with '>='."));
        MUTATIONS.put(Opcodes.IFLE, new Substitution(Opcodes.IFLT, "ROR Mutator: Replaced '>' with '>='."));
    }

    RorMethodVisitorLT(final MethodMutatorFactory factory, final MutationContext context,
            final MethodVisitor delegateMethodVisitor) {
        super(factory, context, delegateMethodVisitor);
    }

    @Override
    protected Map<Integer, Substitution> getMutations() {
        return MUTATIONS;
    }
}