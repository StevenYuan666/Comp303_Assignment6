package Code;

public interface Visitor<T> {
    public T visitMoveForward(MoveForward pMoveForward);
    public T visitTurn(Turn pTurn);
    public T visitGrab(Grab pGrab);
    public T visitRelease(Release pRelease);
    public T visitCompact(Compact pCompact);
    public T visitEmptyCompactor(EmptyCompactor pEmptyCompactor);
    public T visitCompositeAction(CompositeAction pCompositeAction);
    public T visitRechargeBeforeAction(RechargeBeforeAction pRechargeBeforeAction);
    public T visitProgram(Program pProgram);
}
