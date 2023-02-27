using DataLayer.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataLayer.Dal
{
    public interface IRepository
    {
        IEnumerable<Group> GetGroups();
        Task<IEnumerable<Group>> GetGroupsAsync();
        IEnumerable<Match> GetMatches();
        Task<IEnumerable<Match>> GetMatchesAsync();
        IEnumerable<Team> GetTeams();
        Task<IEnumerable<Team>> GetTeamsAsync();
        IEnumerable<Match> GetTeamMatches(string fifaCode);
        Task<IEnumerable<Match>> GetTeamMatchesAsync(string fifaCode);
        IEnumerable<TeamResults> GetTeamResults();
        Task<IEnumerable<TeamResults>> GetTeamResultsAsync();
    }
}
