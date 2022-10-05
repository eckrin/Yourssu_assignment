package yourssu.blog.dto.req

import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class SignUpRequestDTO (
    @field:Email
    @field:NotBlank
    var email:String,
    @field:NotBlank
    var password:String,
    @field:NotBlank
    var username:String
)