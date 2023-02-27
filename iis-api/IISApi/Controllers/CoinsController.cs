using Commons.Xml.Relaxng;
using IISApi.Dal;
using IISApi.Models;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using System.Runtime.Serialization;
using System.Text;
using System.Xml;
using System.Xml.Linq;
using System.Xml.Schema;

// For more information on enabling Web API for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace IISApi.Controllers
{
    [Authorize]
    [Route("api/[controller]")]
    [ApiController]
    public class CoinsController : ControllerBase
    {
        private static readonly IRepository<Coin> repository = new CoinsRepository();

        private const string XSD = @"<?xml version='1.0' encoding='utf-8'?>
<xs:schema xmlns:i='http://www.w3.org/2001/XMLSchema-instance' attributeFormDefault='unqualified' elementFormDefault='qualified' targetNamespace='http://schemas.datacontract.org/2004/07/IISApi.Models' xmlns:xs='http://www.w3.org/2001/XMLSchema'>
	<xs:element name = 'Coin'>
        <xs:complexType>
			<xs:sequence>
				<xs:element name = 'Symbol' type='xs:string' />
				<xs:element name = 'Name' type='xs:string' />
				<xs:element name = 'Price' type='xs:double' />
				<xs:element name = 'PriceChange' type='xs:double' />
				<xs:element name = 'Updated' type='xs:dateTime' />
			</xs:sequence>
		</xs:complexType>
	</xs:element>
</xs:schema>";

        private const string RNG = @"<?xml version = '1.0' encoding='UTF-8'?>
<element name='Coin' xmlns='http://relaxng.org/ns/structure/1.0' ns='http://schemas.datacontract.org/2004/07/IISApi.Models' datatypeLibrary='http://www.w3.org/2001/XMLSchema-datatypes'>
  <element name='Symbol'>
    <text/>
  </element>
  <element name='Name'>
    <text/>
  </element>
  <element name='Price'>
    <data type='double'/>
  </element>
   <element name='PriceChange'>
    <data type='double'/>
  </element>
   <element name='Updated'>
    <data type='dateTime'/>
  </element>
</element>";

        [AllowAnonymous]
        [HttpGet]
        public List<Coin> Get()
        {
            return repository.GetAll();
        }

//        [Authorize("AdminsOnly")]
        [HttpPost("{validation}")]
        public void Post(string validation, [FromBody] string xml)
        {
            string _byteOrderMarkUtf8 = Encoding.UTF8.GetString(Encoding.UTF8.GetPreamble());
            if (xml.StartsWith(_byteOrderMarkUtf8))
            {
                xml = xml.Remove(0, _byteOrderMarkUtf8.Length);
            }

            XmlDocument? document;

            if (validation == "Xsd")
            {
                document = xsdValidation(xml);
            }
            else
            {
                document = rngValidation(xml);
            }

            if (document != null)
            {
                DataContractSerializer deserializer = new(typeof(Coin));
                Coin coin = (Coin)deserializer.ReadObject(new XmlNodeReader(document));

                if (coin != null)
                {
                    repository.Add(coin);
                }
                Response.StatusCode = StatusCodes.Status201Created;
            }
            else
            {
                Response.StatusCode = StatusCodes.Status400BadRequest;
            }
        }

        private XmlDocument? rngValidation(string xml)
        {
            RelaxngValidatingReader reader = new(XmlReader.Create(new StringReader(xml)),
                XmlReader.Create(new StringReader(RNG)));
            try
            {
                while (reader.Read()) { };
                XmlDocument document = new();
                document.LoadXml(xml);
                return document;
            }
            catch (Exception e)
            {
                return null;
            }
        }

        private XmlDocument? xsdValidation(string xml)
        {
            XmlSchemaSet schemas = new XmlSchemaSet();
            schemas.Add("http://schemas.datacontract.org/2004/07/IISApi.Models", XmlReader.Create(new StringReader(XSD)));
            XmlDocument? document = new();
            document.Schemas.Add(schemas);
            document.LoadXml(xml);
            document.Validate((o, e) =>
            {
                document = null;
            });

            return document;
        }
    }
}
