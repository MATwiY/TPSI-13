package wizut.tpsi.ogloszenia.web;

public class OfferFilter {

    private Integer manufacturerId;
    private Integer modelId;
    private Integer fuelId;
    private Integer yearStart;
    private Integer yearStop;

    public Integer getYearStart() {
        return yearStart;
    }

    public void setYearStart(Integer yearStart) {
        this.yearStart = yearStart;
    }

    public Integer getYearStop() {
        return yearStop;
    }

    public void setYearStop(Integer yearStop) {
        this.yearStop = yearStop;
    }

    public Integer getFuelId() {
        return fuelId;
    }

    public void setFuelId(Integer fuelId) {
        this.fuelId = fuelId;
    }

    public Integer getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Integer manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }


}
