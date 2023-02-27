using DataLayer.Exceptions;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataLayer.Helpers
{
    public class ParseStringConverter : JsonConverter
    {
        public override bool CanConvert(Type t) => t == typeof(int);

        public override object ReadJson(JsonReader reader, Type t, object existingValue, JsonSerializer serializer)
        {
            if (reader.TokenType == JsonToken.Null) return 0;
            var value = serializer.Deserialize<string>(reader);
            if (int.TryParse(value, out int i))
            {
                return i;
            }
            throw new JsonConvertException($"Cannot unmarshal type {value} to int");
        }

        public override void WriteJson(JsonWriter writer, object untypedValue, JsonSerializer serializer)
        {
            if (untypedValue == null)
            {
                serializer.Serialize(writer, null);
                return;
            }
            serializer.Serialize(writer, ((int)untypedValue).ToString());
        }
    }
}
