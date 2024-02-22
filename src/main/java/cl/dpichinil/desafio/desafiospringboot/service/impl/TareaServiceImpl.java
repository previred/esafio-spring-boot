package cl.dpichinil.desafio.desafiospringboot.service.impl;

import cl.dpichinil.desafio.desafiospringboot.config.exception.CustomException;
import cl.dpichinil.desafio.desafiospringboot.dto.EstadoTareaDto;
import cl.dpichinil.desafio.desafiospringboot.dto.ResponseDto;
import cl.dpichinil.desafio.desafiospringboot.dto.TareaDto;
import cl.dpichinil.desafio.desafiospringboot.persistence.entity.EstadoTarea;
import cl.dpichinil.desafio.desafiospringboot.persistence.entity.Tarea;
import cl.dpichinil.desafio.desafiospringboot.persistence.repository.EstadoTareaRepository;
import cl.dpichinil.desafio.desafiospringboot.persistence.repository.TareaRepository;
import cl.dpichinil.desafio.desafiospringboot.service.TareaService;
import cl.dpichinil.desafio.desafiospringboot.util.Constant;
import cl.dpichinil.desafio.desafiospringboot.util.FunctionUtil;
import cl.dpichinil.desafio.desafiospringboot.util.ParserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class TareaServiceImpl implements TareaService {
    private final EstadoTareaRepository estadoTareaRepository;
    private final TareaRepository tareaRepository;
    private final FunctionUtil functionUtil;

    @Override
    public ResponseEntity<ResponseDto> create(TareaDto dto) {
        String module = Constant.MODULE_TAREA_CREATE;
        Optional<EstadoTarea> optional = estadoTareaRepository.findById(dto.getEstadoTarea().getId());
        if(optional.isEmpty()) throw new CustomException(HttpStatus.BAD_REQUEST, 1000, module);
        Tarea tarea = ParserUtil.parseTareaDtoToTarea(dto);
        if(tarea.getId() > 0) throw new CustomException(HttpStatus.BAD_REQUEST, 1001, module);
        tarea.setEstadoTarea(optional.get());
        tarea = tareaRepository.save(tarea);
        if(tarea.getId() == 0) throw new CustomException(HttpStatus.BAD_REQUEST, 1002, module);
        ResponseDto response = new ResponseDto(0);
        response = functionUtil.getMessage(response, module);
        response.setData(tarea.getId());
        return functionUtil.generateResponseEntity(HttpStatus.CREATED, response);
    }

    @Override
    public ResponseEntity<ResponseDto> getAll() {
        String module = Constant.MODULE_TAREA_LIST;
        List<Tarea> listEntity = tareaRepository.findAllByActivo(true);
        if(listEntity.isEmpty()) throw new CustomException(HttpStatus.BAD_REQUEST, 1000, module);
        Stream<Tarea> stream = listEntity.stream();
        List<TareaDto> listDto = stream.map(e -> ParserUtil.parseTareaToTareaDtoWithEstado(e)).collect(Collectors.toList());
        ResponseDto response = new ResponseDto(0);
        response = functionUtil.getMessage(response, module);
        response.setData(listDto);
        return functionUtil.generateResponseEntity(HttpStatus.OK, response);
    }


    @Override
    public ResponseEntity<ResponseDto> update(TareaDto dto) {
        String module = Constant.MODULE_TAREA_UPDATE;
        Optional<EstadoTarea> optionalEstado = estadoTareaRepository.findById(dto.getEstadoTarea().getId());
        if(optionalEstado.isEmpty()) throw new CustomException(HttpStatus.BAD_REQUEST, 1000, module);
        Optional<Tarea> optional = tareaRepository.findById(dto.getId());
        if(optional.isEmpty()) throw new CustomException(HttpStatus.BAD_REQUEST, 1001, module);
        Tarea entity = optional.get();
        entity.setDescripcion(dto.getDescripcion());
        entity.setActivo(dto.isActivo());
        entity.setEstadoTarea(optionalEstado.get());
        entity = tareaRepository.save(entity);
        ResponseDto responseDto = new ResponseDto(0);
        responseDto = functionUtil.getMessage(responseDto, module);
        return functionUtil.generateResponseEntity(HttpStatus.OK, responseDto);
    }

    @Override
    public ResponseEntity<ResponseDto> delete(int id) {
        String module = Constant.MODULE_TAREA_DELETE;
        ResponseDto responseDto = null;
        Optional<Tarea> optional = tareaRepository.findById(id);
        if(optional.isEmpty()) throw new CustomException(HttpStatus.BAD_REQUEST, 1000, module);
        Tarea entity = optional.get();
        entity.setActivo(false);
        entity = tareaRepository.save(entity);
        if(entity == null || entity.getId() == 0 || entity.isActivo())
            throw new CustomException(HttpStatus.BAD_REQUEST, 1000, module);
        responseDto = new ResponseDto(0);
        responseDto = functionUtil.getMessage(responseDto, module);
        return functionUtil.generateResponseEntity(HttpStatus.OK, responseDto);
    }

    @Override
    public ResponseEntity<ResponseDto> getAllEstadoTarea() {
        String module = Constant.MODULE_ESTADO_TAREA_LIST;
        List<EstadoTareaDto> listDto;
        List<EstadoTarea> listEntity = estadoTareaRepository.findAll();
        if(listEntity.isEmpty()) throw new CustomException(HttpStatus.BAD_REQUEST, 1000, module);
        Stream<EstadoTarea> stream = listEntity.stream();
        listDto = stream
                .filter(f -> f.isActivo() == true)
                .map(e -> ParserUtil.parseEstadoTareaToEstadoTareaDto(e))
                .collect(Collectors.toList());
        ResponseDto response = new ResponseDto(0);
        response = functionUtil.getMessage(response, module);
        response.setData(listDto);
        return functionUtil.generateResponseEntity(HttpStatus.OK, response);
    }
}
