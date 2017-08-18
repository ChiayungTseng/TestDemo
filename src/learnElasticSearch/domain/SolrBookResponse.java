package learnElasticSearch.domain;

/**
 * Created by zhengjiarong on 2017/8/10.
 */
public class SolrBookResponse {
    private ResponseHeader responseHeader;
    private Response response;

    public ResponseHeader getResponseHeader() {
        return responseHeader;
    }

    public void setResponseHeader(ResponseHeader responseHeader) {
        this.responseHeader = responseHeader;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
