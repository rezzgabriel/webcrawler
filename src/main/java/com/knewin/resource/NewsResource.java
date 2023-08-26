package com.knewin.resource;

import com.knewin.modal.DadosExtraidos;
import com.knewin.modal.FormDataNews;
import com.knewin.modal.ResultPage;
import com.knewin.service.NewsService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Path("/news")
public class NewsResource {

    @Inject
    @RestClient
    NewsService newsService;

    DateTimeFormatter formatoDataConteudo = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    SimpleDateFormat formatoBuscaLinks = new SimpleDateFormat("dd.MM.yy");
    String url = "https://www.infomoney.com.br/mercados/";

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<DadosExtraidos> buscaDados() throws IOException {
        Document document = Jsoup.connect(url).get(); // Obtendo a primeira página de notícias
        Date dataColeta = new Date();
        FormDataNews formDataNews = new FormDataNews();
        ArrayList<DadosExtraidos> listDadosExtraidos = new ArrayList<DadosExtraidos>();

        ArrayList<String> lista = new ArrayList<String>();

        // Iniciando a coleta dos links da primeira página
        Elements articleCardsFirstPage = document.select(".article-card__headline-link");

        for (Element articleCard : articleCardsFirstPage) {
            Elements linksInArticle = articleCard.select("a[href]"); // Selecionar links dentro de cada article-card

            for (Element link : linksInArticle) {
                String linkHref = link.attr("abs:href"); // Obter o link completo
                lista.add(linkHref);
            }
        }


        for (int i = 2; i < 4; i++) { // Obtendo as duas próximas páginas de notícias
            formDataNews.action = "infinite_scroll";
            formDataNews.page = String.valueOf(i);
            formDataNews.currentDay = formatoBuscaLinks.format(dataColeta);
            formDataNews.order = "DESC";

            ResultPage resultPage = newsService.buscar(formDataNews);

            String html = resultPage.getHtml();
            Document doc = Jsoup.parse(html);

            Elements articleCardsNextPages = doc.select(".article-card__headline-link");

            for (Element articleCard : articleCardsNextPages) {
                Elements linksInArticle = articleCard.select("a[href]"); // Selecionar links dentro de cada article-card

                for (Element link : linksInArticle) {
                    String linkHref = link.attr("abs:href"); // Obter o link completo
                    lista.add(linkHref);
                }
            }
        }

        // Acessando cada notícia para coleta das informações
        lista.forEach(noticia->{
            try {
                StringBuilder resultadoConteudo = new StringBuilder();
                Document doc = Jsoup.connect(noticia).get();

                String titulo = doc.select("h1").text();
                String subtitulo = doc.select("div.single__excerpt p").text();
                String autor = doc.select("div.single__author-info a").text();

                String dataBruta = doc.select("time[datetime]").attr("datetime");
                OffsetDateTime novaData = OffsetDateTime.parse(dataBruta);
                String dataFormatada = novaData.format(formatoDataConteudo);

                Elements conteudos = doc.select("div.single__content");

                for (Element conteudo : conteudos) {
                    resultadoConteudo.append(conteudo.text()).append(" "); // Separando os paragrafos com espaço após ponto final
                }

                DadosExtraidos dadosExtraidos = new DadosExtraidos();
                dadosExtraidos.setUrl(noticia);
                dadosExtraidos.setTitulo(titulo);
                dadosExtraidos.setSubtitulo(subtitulo);
                dadosExtraidos.setAutor(autor);
                dadosExtraidos.setData(dataFormatada);
                dadosExtraidos.setConteudo(resultadoConteudo.toString());

                listDadosExtraidos.add(dadosExtraidos);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        return listDadosExtraidos;
    }

}
