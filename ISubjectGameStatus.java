public interface ISubjectGameStatus  
{
    public void register(IObserveGameStatus observer);
    public void unRegister(IObserveGameStatus observer);
    public void notifyObservers(Boolean hitMiss);
}
