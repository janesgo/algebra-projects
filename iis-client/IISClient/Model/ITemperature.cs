using CookComputing.XmlRpc;

namespace IISClient.Model
{
    [XmlRpcUrl("http://localhost:8081")]
    public interface ITemperature : IXmlRpcProxy
    {
        [XmlRpcMethod("handler.getTemperature")]
        double GetTemperature(string city);
    }
}
