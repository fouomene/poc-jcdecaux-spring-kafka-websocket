package events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "number",
        "contract_name",
        "name",
        "address",
        "banking",
        "bonus",
        "bike_stands",
        "available_bike_stands",
        "available_bikes",
        "status",
        "last_update"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class StationEvent {

    @JsonProperty("number")
    private Integer number;
    @JsonProperty("contract_name")
    private String contractName;
    @JsonProperty("name")
    private String name;
    @JsonProperty("address")
    private String address;
    @JsonProperty("banking")
    private Boolean banking;
    @JsonProperty("bonus")
    private Boolean bonus;
    @JsonProperty("bike_stands")
    private Integer bikeStands;
    @JsonProperty("available_bike_stands")
    private Integer availableBikeStands;
    @JsonProperty("available_bikes")
    private Integer availableBikes;
    @JsonProperty("status")
    private String status;
    @JsonProperty("last_update")
    private Long lastUpdate;

    public StationEvent() {
    }

    @JsonProperty("number")
    public Integer getNumber() {
        return number;
    }

    @JsonProperty("number")
    public void setNumber(Integer number) {
        this.number = number;
    }

    @JsonProperty("contract_name")
    public String getContractName() {
        return contractName;
    }

    @JsonProperty("contract_name")
    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(String address) {
        this.address = address;
    }

    @JsonProperty("banking")
    public Boolean getBanking() {
        return banking;
    }

    @JsonProperty("banking")
    public void setBanking(Boolean banking) {
        this.banking = banking;
    }

    @JsonProperty("bonus")
    public Boolean getBonus() {
        return bonus;
    }

    @JsonProperty("bonus")
    public void setBonus(Boolean bonus) {
        this.bonus = bonus;
    }

    @JsonProperty("bike_stands")
    public Integer getBikeStands() {
        return bikeStands;
    }

    @JsonProperty("bike_stands")
    public void setBikeStands(Integer bikeStands) {
        this.bikeStands = bikeStands;
    }

    @JsonProperty("available_bike_stands")
    public Integer getAvailableBikeStands() {
        return availableBikeStands;
    }

    @JsonProperty("available_bike_stands")
    public void setAvailableBikeStands(Integer availableBikeStands) {
        this.availableBikeStands = availableBikeStands;
    }

    @JsonProperty("available_bikes")
    public Integer getAvailableBikes() {
        return availableBikes;
    }

    @JsonProperty("available_bikes")
    public void setAvailableBikes(Integer availableBikes) {
        this.availableBikes = availableBikes;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("last_update")
    public Long getLastUpdate() {
        return lastUpdate;
    }

    @JsonProperty("last_update")
    public void setLastUpdate(Long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "Station{" +
                "number=" + number +
                ", contractName='" + contractName + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", banking=" + banking +
                ", bonus=" + bonus +
                ", bikeStands=" + bikeStands +
                ", availableBikeStands=" + availableBikeStands +
                ", availableBikes=" + availableBikes +
                ", status='" + status + '\'' +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}