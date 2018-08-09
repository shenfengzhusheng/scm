package org.xfs.scm.common.baidu.map.api.response;

import java.io.Serializable;
import java.util.List;

public class PlaceSearchResponse implements Serializable {
    private int status;
    private String message;
    private List<PoisModel> results;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<PoisModel> getResults() {
        return results;
    }

    public void setResults(List<PoisModel> results) {
        this.results = results;
    }
}
