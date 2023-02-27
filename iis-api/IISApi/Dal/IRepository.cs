namespace IISApi.Dal
{
    public interface IRepository<TEntity>
    {
        List<TEntity> GetAll();
        void Add(TEntity entity);
    }
}
