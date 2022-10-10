package se.ifmo.ru.storage.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Page<T> {
    private List<T> objects;
    private Integer page;
    private Integer pageSize;
    private Integer totalPages;
    private Long totalCount;
}
