package compiler.asm.converter;

import compiler.ConstDef;
import compiler.asm.*;
import compiler.asm.operand.RegShiftImmOperand;
import compiler.asm.operand.ShiftOp;
import compiler.genir.code.InterRepresent;
import compiler.genir.code.LSRepresent;
import compiler.genir.code.LoadRepresent;
import compiler.symboltable.FuncSymbol;
import compiler.symboltable.ParamSymbol;
import compiler.symboltable.ValueSymbol;
import compiler.symboltable.VarSymbol;

import java.util.Collection;
import java.util.List;

public class LoadConverter extends LSConverter {
    @Override
    public boolean needProcess(InterRepresent ir, Collection<InterRepresent> allIR, int index) {
        return ir instanceof LoadRepresent;
    }

    @Override
    public int process(AsmBuilder builder, RegGetter regGetter, InterRepresent ir, List<InterRepresent> allIR, int index, FuncSymbol funcSymbol) {
        return super.process(AsmBuilder.Mem.LDR, builder, regGetter, (LSRepresent) ir,funcSymbol,
                             regGetter.getReg(ir, ((LSRepresent) ir).target));
    }

}
