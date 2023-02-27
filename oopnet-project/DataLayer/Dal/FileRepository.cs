using DataLayer.Models;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Reflection;
using System.Text;
using System.Threading.Tasks;
using static DataLayer.Models.Enums;

namespace DataLayer.Dal
{
    class FileRepository : IRepository
    {
        private const string DATA_PATH = @"\data\";
        private const string GROUP_RESULTS_FILENAME = @"\group_results.json";
        private const string MATCHES_FILENAME = @"\matches.json";
        private const string RESULTS_FILENAME = @"\results.json";
        private const string TEAMS_FILENAME = @"\teams.json";

        private readonly string PATH = Path.GetDirectoryName(Assembly.GetExecutingAssembly().Location);
        private readonly string genderName;
        private readonly string fullPath;

        public FileRepository(Gender gender)
        {
            genderName = Enum.GetName(typeof(Gender), gender);
            fullPath = PATH + DATA_PATH + genderName;
        }

        public IEnumerable<Group> GetGroups() => JsonConvert.DeserializeObject<IEnumerable<Group>>(File.ReadAllText(fullPath + GROUP_RESULTS_FILENAME));

        public IEnumerable<Match> GetMatches() => JsonConvert.DeserializeObject<IEnumerable<Match>>(File.ReadAllText(fullPath + MATCHES_FILENAME));

        public IEnumerable<Team> GetTeams() => JsonConvert.DeserializeObject<IEnumerable<Team>>(File.ReadAllText(fullPath + TEAMS_FILENAME));

        public IEnumerable<Match> GetTeamMatches(string fifaCode) => new List<Match>(GetMatches().ToList().FindAll(e => e.AwayTeam.Code == fifaCode || e.HomeTeam.Code == fifaCode));

        public IEnumerable<TeamResults> GetTeamResults() => JsonConvert.DeserializeObject<IEnumerable<TeamResults>>(File.ReadAllText(fullPath + RESULTS_FILENAME));

        public Task<IEnumerable<Group>> GetGroupsAsync() => throw new NotImplementedException();

        public Task<IEnumerable<Match>> GetMatchesAsync() => throw new NotImplementedException();

        public Task<IEnumerable<Team>> GetTeamsAsync() => throw new NotImplementedException();

        public Task<IEnumerable<Match>> GetTeamMatchesAsync(string fifaCode) => throw new NotImplementedException();

        public Task<IEnumerable<TeamResults>> GetTeamResultsAsync() => throw new NotImplementedException();
    }
}
