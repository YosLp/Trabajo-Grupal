package co.edu.udes.activity.backend.demo.codegen.datafetchers;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import graphql.schema.DataFetchingEnvironment;
import jakarta.annotation.Generated;
import java.lang.String;

@Generated("com.netflix.graphql.dgs.codegen.CodeGen")
@co.edu.udes.activity.backend.demo.codegen.Generated
@DgsComponent
public class HelloDatafetcher {
  @DgsData(
      parentType = "Query",
      field = "hello"
  )
  public String getHello(DataFetchingEnvironment dataFetchingEnvironment) {
    return "";
  }
}
