using DataLayer.Models;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using static DataLayer.Models.Enums;

namespace DataLayer.Dal
{
    class WebApiRepository : IRepository
    {
        private const string MEN = "http://world-cup-json-2018.herokuapp.com";
        private const string WOMEN = "http://worldcup.sfg.io";
        private const string GROUP_RESULTS = "/teams/group_results";
        private const string MATCHES = "/matches";
        private const string TEAM_MATCHES = "/matches/country?fifa_code=";
        private const string RESULTS = "/teams/results";
        private const string TEAMS = "/teams";

        private readonly string genderUri;

        public WebApiRepository(Gender gender)
        {
            if (gender == Gender.Men)
            {
                genderUri = MEN;
            }
            else
            {
                genderUri = WOMEN;
            }
        }

        public IEnumerable<Group> GetGroups()
        {
            using (HttpClient client = new HttpClient())
            {
                string json = client.GetStringAsync(genderUri + GROUP_RESULTS).Result;
                return JsonConvert.DeserializeObject<IEnumerable<Group>>(json);
            }
        }

        public async Task<IEnumerable<Group>> GetGroupsAsync()
        {
            using (HttpClient client = new HttpClient())
            {
                string json = await client.GetStringAsync(genderUri + GROUP_RESULTS);
                return JsonConvert.DeserializeObject<IEnumerable<Group>>(json);
            }
        }

        public IEnumerable<Match> GetMatches()
        {
            using (HttpClient client = new HttpClient())
            {
                string json = client.GetStringAsync(genderUri + MATCHES).Result;
                return JsonConvert.DeserializeObject<IEnumerable<Match>>(json);
            }
        }

        public async Task<IEnumerable<Match>> GetMatchesAsync()
        {
            using (HttpClient client = new HttpClient())
            {
                string json = await client.GetStringAsync(genderUri + MATCHES);
                return JsonConvert.DeserializeObject<IEnumerable<Match>>(json);
            }
        }

        public IEnumerable<Team> GetTeams()
        {
            using (HttpClient client = new HttpClient())
            {
                string json = client.GetStringAsync(genderUri + TEAMS).Result;
                return JsonConvert.DeserializeObject<IEnumerable<Team>>(json);
            }
        }

        public async Task<IEnumerable<Team>> GetTeamsAsync()
        {
            using (HttpClient client = new HttpClient())
            {
                string json = await client.GetStringAsync(genderUri + TEAMS);
                return JsonConvert.DeserializeObject<IEnumerable<Team>>(json);
            }
        }

        public IEnumerable<Match> GetTeamMatches(string fifaCode)
        {
            using (HttpClient client = new HttpClient())
            {
                string json = client.GetStringAsync(genderUri + TEAM_MATCHES + fifaCode).Result;
                return JsonConvert.DeserializeObject<IEnumerable<Match>>(json);
            }
        }

        public async Task<IEnumerable<Match>> GetTeamMatchesAsync(string fifaCode)
        {
            using (HttpClient client = new HttpClient())
            {
                string json = await client.GetStringAsync(genderUri + TEAM_MATCHES + fifaCode);
                return JsonConvert.DeserializeObject<IEnumerable<Match>>(json);
            }
        }

        public IEnumerable<TeamResults> GetTeamResults()
        {
            using (HttpClient client = new HttpClient())
            {
                string json = client.GetStringAsync(genderUri + RESULTS).Result;
                return JsonConvert.DeserializeObject<IEnumerable<TeamResults>>(json);
            }
        }

        public async Task<IEnumerable<TeamResults>> GetTeamResultsAsync()
        {
            using (HttpClient client = new HttpClient())
            {
                string json = await client.GetStringAsync(genderUri + RESULTS);
                return JsonConvert.DeserializeObject<IEnumerable<TeamResults>>(json);
            }
        }
    }
}
