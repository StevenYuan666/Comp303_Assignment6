package Code;

//Implement a concrete visitor to compute total distance to move
public class GetDistance implements Visitor<Double>{
    @Override
    public Double visitMoveForward(MoveForward pMoveForward) {
        return pMoveForward.getDistance();
    }

    @Override
    public Double visitTurn(Turn pTurn) {
        return 0.0;
    }

    @Override
    public Double visitGrab(Grab pGrab) {
        return 0.0;
    }

    @Override
    public Double visitRelease(Release pRelease) {
        return 0.0;
    }

    @Override
    public Double visitCompact(Compact pCompact) {
        return 0.0;
    }

    @Override
    public Double visitEmptyCompactor(EmptyCompactor pEmptyCompactor) {
        return 0.0;
    }

    @Override
    public Double visitCompositeAction(CompositeAction pCompositeAction) {
        Double total = 0.0;
        for(BasicAction action : pCompositeAction.getAllActions()){
            //Safe to do the down casting here
            total += (Double) action.accept(this);
        }
        return total;
    }

    @Override
    public Double visitRechargeBeforeAction(RechargeBeforeAction pRechargeBeforeAction) {
        //Safe to do the down casting here
        return (Double) pRechargeBeforeAction.getAction().accept(this);
    }

    @Override
    public Double visitProgram(Program pProgram) {
        Double total = 0.0;
        for(Action action : pProgram.getAllActions()){
            //Safe to do the down casting here
            total += (Double) action.accept(this);
        }
        return total;
    }
}
