package common;

import ast.AstNode;
import ast.IAstValue;
import ast.Utils;
import common.symbol.Variable;
import asm.IName;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class OffsetVar implements IName {
    public Variable variable;
    public AstNode offsetTree;
    public boolean isLVal = false;
    public boolean isAddress = false;

    public OffsetVar(Variable array, AstNode offsetTree) {
        this.variable = array;
        this.offsetTree = offsetTree;
    }

    public OffsetVar(Variable array, int offsetTree) {
        this.variable = array;
        this.offsetTree = AstNode.makeLeaf(offsetTree);
    }

    public IAstValue getOffset() {
        return offsetTree.value;
    }

    public Set<IName> parseSelf() {
        Set<IName> names = new HashSet<>();
        names.add(variable);
        if (getOffset() instanceof IName) names.add(((IName) getOffset()));
        return names;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OffsetVar offsetVar = (OffsetVar) o;
        return Objects.equals(variable, offsetVar.variable) && Objects.equals(offsetTree.value, offsetVar.offsetTree.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(variable, offsetTree.value);
    }

    @Override
    public String toString() {
        if (null == offsetTree.value)
            return variable.name + "[exp]";
        else
            return variable.name + '[' + offsetTree.value + ']';
    }


    @Override
    public Object getVal() {
        return variable;
    }
}
