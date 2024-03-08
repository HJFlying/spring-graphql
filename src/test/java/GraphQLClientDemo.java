import com.adel.springgraphql.Book;
import org.junit.jupiter.api.Test;
import org.springframework.graphql.client.GraphQlClient;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.web.reactive.function.client.WebClient;

public class GraphQLClientDemo {
    @Test
    public void testVariables(){
        WebClient wc = WebClient.builder().baseUrl("http://localhost:8080/graphql").build();
        GraphQlClient client = HttpGraphQlClient.builder(wc).build();
        //验证测试，$id参数名必须和id一致，否则报错。
        String body = "query ($id:ID!) {\n" +
                "        bookById(id:$id) {\n" +
                "            id\n" +
                "            name\n" +
                "            pageCount\n" +
                "            author {\n" +
                "                id\n" +
                "                firstName\n" +
                "                lastName\n" +
                "            }\n" +
                "        }\n" +
                "    }";
        Book book = client.document(body)
                .variable("id", "book-1")
                .retrieve("bookById")
                .toEntity(Book.class)
                .block();
        System.out.println(book.getName());
    }
}
