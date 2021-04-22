package Code;

//Implement a concrete visitor to
public class GetNumOfCompacted implements Visitor<Integer>{
    @Override
    public Integer visitMoveForward(MoveForward pMoveForward) {
        return 0;
    }

    @Override
    public Integer visitTurn(Turn pTurn) {
        return 0;
    }

    @Override
    public Integer visitGrab(Grab pGrab) {
        return 0;
    }

    @Override
    public Integer visitRelease(Release pRelease) {
        return 0;
    }

    @Override
    public Integer visitCompact(Compact pCompact) {
        //We don't check the precondition in the visitor
        return 1;
    }

    @Override
    public Integer visitEmptyCompactor(EmptyCompactor pEmptyCompactor) {
        return 0;
    }

    @Override
    public Integer visitCompositeAction(CompositeAction pCompositeAction) {
        Integer total = 0;
        for(BasicAction action : pCompositeAction.getAllActions()){
            //Safe to do the down casting here
            total += (Integer) action.accept(this);
        }
        return total;
    }

    @Override
    public Integer visitRechargeBeforeAction(RechargeBeforeAction pRechargeBeforeAction) {
        //Safe to do the down casting here
        return (Integer) pRechargeBeforeAction.getAction().accept(this);
    }

    @Override
    public Integer visitProgram(Program pProgram) {
        Integer total = 0;
        for(Action action : pProgram.getAllActions()){
            //Safe to do the down casting here
            total += (Integer) action.accept(this);
        }
        return total;
    }
}
