using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataLayer.Exceptions
{
    public class JsonConvertException : Exception
    {
        public JsonConvertException() { }
        public JsonConvertException(string message) : base(message) { }
    }
}
