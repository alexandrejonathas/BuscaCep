package cep.busca.unifavip.edu.br.buscacep.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jlima on 03/12/2017.
 */

public class Endereco implements Parcelable {

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String unidade;
    private String ibge;
    private String gia;

    public static final Creator<Endereco> CREATOR = new Creator<Endereco>() {
        @Override
        public Endereco createFromParcel(Parcel in) {
            Endereco end = new Endereco(in);
            end.readFromParcel(in);
            return new Endereco(in);
        }

        @Override
        public Endereco[] newArray(int size) {
            return new Endereco[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    protected Endereco(Parcel in) {
        setCep(in.readString());
        setLogradouro(in.readString());
        setComplemento(in.readString());
        setBairro(in.readString());
        setLocalidade(in.readString());
        setUf(in.readString());
        setUnidade(in.readString());
        setIbge(in.readString());
        setGia(in.readString());
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(getCep());
        parcel.writeString(getLogradouro());
        parcel.writeString(getComplemento());
        parcel.writeString(getBairro());
        parcel.writeString(getLocalidade());
        parcel.writeString(getUf());
        parcel.writeString(getUnidade());
        parcel.writeString(getIbge());
        parcel.writeString(getGia());
    }


    public void readFromParcel(Parcel parcel){

        this.setCep(parcel.readString());

        this.setLogradouro(parcel.readString());

        this.setComplemento(parcel.readString());

        this.setBairro(parcel.readString());

        this.setLocalidade(parcel.readString());

        this.setUf(parcel.readString());

        this.setUnidade(parcel.readString());

        this.setIbge(parcel.readString());

        this.setGia(parcel.readString());

    }


    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getIbge() {
        return ibge;
    }

    public void setIbge(String ibge) {
        this.ibge = ibge;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }
}
