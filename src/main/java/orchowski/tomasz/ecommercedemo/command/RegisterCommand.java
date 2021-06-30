package orchowski.tomasz.ecommercedemo.command;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class RegisterCommand {
    private String username;
    @ToString.Exclude
    private String password;
    private String email;
}
