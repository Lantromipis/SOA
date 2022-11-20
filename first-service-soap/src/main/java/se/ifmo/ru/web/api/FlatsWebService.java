package se.ifmo.ru.web.api;

import se.ifmo.ru.web.exception.NotFoundException;
import se.ifmo.ru.web.exception.VerificationException;
import se.ifmo.ru.web.model.*;

import javax.jws.Oneway;
import javax.jws.WebService;

@WebService
public interface FlatsWebService {
    FlatsGetListResponseDto getFlats(FlatsGetListRequestDto requestDto);

    FlatGetResponseDto getFlat(long id) throws NotFoundException;

    FlatGetResponseDto addFlat(FlatAddOrUpdateRequestDto requestDto) throws VerificationException;

    FlatGetResponseDto updateFlat(long id, FlatAddOrUpdateRequestDto requestDto) throws VerificationException;

    @Oneway
    void deleteFlat(long id) throws NotFoundException;

    long sumHeight();

    FlatGetResponseDto getWithMaxId() throws NotFoundException;

    long countByNew(Boolean value);
}
