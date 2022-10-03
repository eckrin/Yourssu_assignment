package yourssu.blog.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Comment (

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var comment_id:Long,

    @Column(nullable = false, length = 255)
    var content:String,

    @CreationTimestamp
    var created_at:LocalDateTime,

    @UpdateTimestamp
    var updated_at:LocalDateTime,

    @ManyToOne
    @JoinColumn(name = "article_id")
    var article_id:Article,

    @ManyToOne
    @JoinColumn(name = "user_id")
    var user_id:User

)