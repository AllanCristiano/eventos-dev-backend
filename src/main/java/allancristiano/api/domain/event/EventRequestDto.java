package allancristiano.api.domain.event;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public record EventRequestDto(
    String title, String description, Date date,
    String city, String uf, Boolean remote,
    String eventUrl, MultipartFile image
    ) {
    
}
