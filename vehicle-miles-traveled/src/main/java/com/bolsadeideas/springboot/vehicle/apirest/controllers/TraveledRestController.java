package com.bolsadeideas.springboot.vehicle.apirest.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bolsadeideas.springboot.vehicle.apirest.exception.handler.StandarizedExceptionResponse;
import com.bolsadeideas.springboot.vehicle.apirest.models.entity.County;
import com.bolsadeideas.springboot.vehicle.apirest.models.entity.State;
import com.bolsadeideas.springboot.vehicle.apirest.models.entity.VMTCounty;
import com.bolsadeideas.springboot.vehicle.apirest.models.services.ICountyServices;
import com.bolsadeideas.springboot.vehicle.apirest.models.services.IStateService;
import com.bolsadeideas.springboot.vehicle.apirest.models.services.IVMTCountyService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/traveled")
public class TraveledRestController {

	@Autowired
	private ICountyServices countyServices;
	@Autowired
	private IStateService stateService;
	@Autowired
	private IVMTCountyService vmtcountyService;

	@Operation(summary = "Obtiene un listado de countys y states a cuales pertenecen")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = County.class)) }) })
	@GetMapping("/county/listar")
	@CrossOrigin
	public ResponseEntity<List<County>> county() {
		return ResponseEntity.status(HttpStatus.OK).body(countyServices.findAll());
	}

	@Operation(summary = "Obtiene un listado de states")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = State.class)) }) })
	@GetMapping("/state/listar")
	@CrossOrigin
	public ResponseEntity<List<State>> state() {
		return ResponseEntity.status(HttpStatus.OK).body(stateService.findAll());
	}

	@Operation(summary = "Obtiene un listado de la actividad de vehiculos por día")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = VMTCounty.class)) }) })
	@GetMapping("/vmtcounty/listar")
	@CrossOrigin
	public ResponseEntity<List<VMTCounty>> vmtCounty() {
		return ResponseEntity.status(HttpStatus.OK).body(vmtcountyService.findAll());
	}

	@Operation(summary = "Crea un nuevo registro de county")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Creado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = County.class)) }),
			@ApiResponse(responseCode = "400", description = "Existen campos inválidos", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = StandarizedExceptionResponse.class)) }),
			@ApiResponse(responseCode = "404", description = "State no existe o no se ha especificado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = StandarizedExceptionResponse.class)) }) })
	@PostMapping("county/createcounty")
	@CrossOrigin
	public ResponseEntity<County> createCounty(@Valid @RequestBody County county) {
		return ResponseEntity.status(HttpStatus.CREATED).body(countyServices.add(county));
	}

	@Operation(summary = "Crea un nuevo registro de state")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Creado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = State.class)) }),
			@ApiResponse(responseCode = "400", description = "Existen campos inválidos", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = StandarizedExceptionResponse.class)) }) })
	@PostMapping("state/createstate")
	@CrossOrigin
	public ResponseEntity<State> createState(@Valid @RequestBody State state) {
		return ResponseEntity.status(HttpStatus.CREATED).body(stateService.add(state));
	}

	@Operation(summary = "Crea un nuevo registro de actividad de vehiculos por día")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Creado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = VMTCounty.class)) }),
			@ApiResponse(responseCode = "400", description = "Existen campos inválidos", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = StandarizedExceptionResponse.class)) }),
			@ApiResponse(responseCode = "404", description = "County no existe o no se ha especificado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = StandarizedExceptionResponse.class)) }) })
	@PostMapping("vmtcounty/createvmtcounty")
	@CrossOrigin
	public ResponseEntity<VMTCounty> createCounty(@Valid @RequestBody VMTCounty vmtcounty) {
		return ResponseEntity.status(HttpStatus.CREATED).body(vmtcountyService.add(vmtcounty));
	}

	@Operation(summary = "Modifica un county ya existente")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Resgistro Modificado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = County.class)) }),
			@ApiResponse(responseCode = "404", description = "County o State ingresado para relacionar no existe o no se ha especificado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = StandarizedExceptionResponse.class)) }),
			@ApiResponse(responseCode = "400", description = "Existen campos inválidos", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = StandarizedExceptionResponse.class)) }) })
	@PutMapping("county/modifycounty")
	@CrossOrigin
	public ResponseEntity<County> modifyCounty(@Valid @RequestBody County county) {
		return ResponseEntity.status(HttpStatus.OK).body(countyServices.modify(county));
	}

	@Operation(summary = "Modifica un state ya existente")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Resgistro Modificado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = State.class)) }),
			@ApiResponse(responseCode = "404", description = "State ingresado para modificar no existe o no se ha especificado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = StandarizedExceptionResponse.class)) }),
			@ApiResponse(responseCode = "400", description = "Existen campos inválidos", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = StandarizedExceptionResponse.class)) }) })
	@PutMapping("state/modifystate")
	@CrossOrigin
	public ResponseEntity<State> modifyState(@Valid @RequestBody State state) {
		return ResponseEntity.status(HttpStatus.OK).body(stateService.modify(state));
	}

	@Operation(summary = "Modifica un registro de actividad de vehiculos por día")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Resgistro Modificado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = VMTCounty.class)) }),
			@ApiResponse(responseCode = "404", description = "Registro a modificar no existe o County ingresado para relacionar no existe o no se ha especificado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = StandarizedExceptionResponse.class)) }),
			@ApiResponse(responseCode = "400", description = "Existen campos inválidos", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = StandarizedExceptionResponse.class)) }) })
	@PutMapping("vmtcounty/modifyvmtcounty")
	@CrossOrigin
	public ResponseEntity<VMTCounty> modifyCounty(@Valid @RequestBody VMTCounty vmtcounty) {
		return ResponseEntity.status(HttpStatus.OK).body(vmtcountyService.modify(vmtcounty));
	}

	@Operation(summary = "Elimina un registro county")
	@Parameter(name = "id", description = "Id de County")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Eliminado", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", description = "No existe o no se ha espeificado county a eliminar", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = StandarizedExceptionResponse.class)) }) })
	@DeleteMapping("county/deletecounty/{id}")
	@CrossOrigin
	public ResponseEntity<String> deleteCounty(@PathVariable(name = "id") Long id) {
		countyServices.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@Operation(summary = "Elimina un registro state")
	@Parameter(name = "id", description = "Id de State")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Eliminado", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", description = "No existe o no se ha espeificado state a eliminar", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = StandarizedExceptionResponse.class)) }) })
	@DeleteMapping("state/deletestate/{id}")
	@CrossOrigin
	public ResponseEntity<String> deleteState(@PathVariable(name = "id") Long id) {
		stateService.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@Operation(summary = "Elimina un registro de actividad de vehiculos por día")
	@DeleteMapping("vmtcounty/deletevmtcounty/{id}")
	@Parameter(name = "id", description = "Id de registro de actividad de vehiculos por dia")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Eliminado", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", description = "No existe o no se ha espeificado registro a eliminar", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = StandarizedExceptionResponse.class)) }) })
	@CrossOrigin
	public ResponseEntity<String> deletevmtcounty(@PathVariable Long id) {
		vmtcountyService.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
