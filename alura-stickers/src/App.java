import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {

    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP e buscar os top 250 filmes

        // String url = "https://imdb-api.com/en/API/Top250Movies/k_0ojt0yvm";
        // String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java/api/TopMovies.json";
        // String  url = "https://api.mocki.io/v2/549a5d8b/MostPopularTVs";
        
        String url = "http://localhost:8080/linguagens";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();

        // String url = "https://api.mocki.io/v2/549a5d8b/NASA-APOD";
        // String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java/api/NASA-APOD.json";
        // ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        // exibir e manipular os dados 
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var geradora = new GeradoraDeFigurinhas();
        /* */
        System.out.println(conteudos.size()+" imagens.");
         /* */
        for (int i = 0; i < conteudos.size(); i++) {

            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = "saida/" + conteudo.getTitulo().replace(":", "-") + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.getTitulo());
            System.out.println();
        }
    }
}
