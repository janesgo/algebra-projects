using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using static DataLayer.Models.Enums;

namespace DataLayer.Dal
{
    public static class RepositoryFactory
    {
        public static IRepository GetRepository(Gender gender, AccessType accessType)
        {
            return accessType == AccessType.File ? new FileRepository(gender) : (IRepository)new WebApiRepository(gender);
        }
    }
}
