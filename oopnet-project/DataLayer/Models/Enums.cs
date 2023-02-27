using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;
using System.Threading.Tasks;

namespace DataLayer.Models
{
    public class Enums
    {
        public enum AccessType
        {
            File,
            Web
        }

        public enum Language
        {
            Croatian,
            English
        }

        public enum Gender
        {
            Men,
            Women
        }

        public enum ScreenSize
        {
            Fullscreen,
            Small,
            Medium,
            Large
        }

        public enum Position
        {
            Defender,
            Forward,
            Goalie,
            Midfield
        }

        public enum StageName
        {
            Final,
            [EnumMember(Value = "First Stage")]
            FirstStage,
            [EnumMember(Value = "Match For Third Place")]
            MatchForThirdPlace,
            [EnumMember(Value = "Play-off for third place")]
            PlayOffForThirdPlace = MatchForThirdPlace,
            [EnumMember(Value = "Quarter-Final")]
            QuarterFinal,
            [EnumMember(Value = "Quarter-finals")]
            QuarterFinals = QuarterFinal,
            [EnumMember(Value = "Round of 16")]
            RoundOf16,
            [EnumMember(Value = "Semi-final")]
            SemiFinal,
            [EnumMember(Value = "Semi-finals")]
            SemiFinals = SemiFinal
        }

        public enum Status
        {
            Completed
        }

        public enum Time
        {
            [EnumMember(Value = "full-time")]
            FullTime
        }

        public enum TypeOfEvent
        {
            Goal,
            [EnumMember(Value = "goal-own")]
            GoalOwn,
            [EnumMember(Value = "goal-penalty")]
            GoalPenalty,
            [EnumMember(Value = "red-card")]
            RedCard,
            [EnumMember(Value = "substitution-in")]
            SubstitutionIn,
            [EnumMember(Value = "substitution-out")]
            SubstitutionOut,
            [EnumMember(Value = "yellow-card")]
            YellowCard,
            [EnumMember(Value = "yellow-card-second")]
            YellowCardSecond
        }
    }
}
