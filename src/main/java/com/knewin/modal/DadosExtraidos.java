package com.knewin.modal;

public class DadosExtraidos {

    private String url;
    private String titulo;
    private String subtitulo;
    private String autor;
    private String data;
    private String conteudo;

    public DadosExtraidos() {
    }

    public DadosExtraidos(String url, String titulo, String subtitulo, String autor, String data, String conteudo) {
        this.url = url;
        this.titulo = titulo;
        this.subtitulo = subtitulo;
        this.autor = autor;
        this.data = data;
        this.conteudo = conteudo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
}
