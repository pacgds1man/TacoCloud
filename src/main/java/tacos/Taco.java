// tag::all[]
// tag::allButValidation[]
package tacos;

import java.util.Date;
import java.util.List;
// end::allButValidation[]
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
// tag::allButValidation[]
import lombok.Data;

@Data
public class Taco {

    private Long id;
    private Date createdAt;
    private String name;
    private List<Ingredient> ingredients;

}
//end::allButValidation[]
//tag::end[]