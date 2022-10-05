package yourssu.blog.service

import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import yourssu.blog.dto.res.SignUpResponseDTO
import yourssu.blog.entity.User
import yourssu.blog.exception.userservice.EmailExistsException
import yourssu.blog.exception.userservice.PasswordIncorrectException
import yourssu.blog.exception.userservice.UserNotFoundException
import yourssu.blog.repository.UserRepository
import javax.transaction.Transactional

@Slf4j
@Service
class UserService {

    @Autowired
    private lateinit var userRepository: UserRepository
    @Autowired
    private lateinit var encoder: BCryptPasswordEncoder

    @Transactional
    fun signUp(email:String, password:String, username:String):SignUpResponseDTO {
        if(userRepository.existsByEmail(email)) {
            throw EmailExistsException("중복된 이메일입니다.")
        }
        userRepository.save(
            User(email, encoder.encode(password), username)
        )

        return SignUpResponseDTO(email, username)
    }

    @Transactional
    fun withdraw(email: String, password: String) {
        var user = userRepository.findByEmail(email)
        if(user==null) {
            throw UserNotFoundException("해당 유저가 존재하지 않습니다.")
        }
        if(!encoder.matches(password, user.password)) {
            throw PasswordIncorrectException("비밀번호가 틀립니다.")
        }
        userRepository.deleteById(user.user_id)
    }
}