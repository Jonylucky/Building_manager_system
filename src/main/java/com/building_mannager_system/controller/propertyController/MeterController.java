package com.building_mannager_system.controller.propertyController;

import com.building_mannager_system.dto.requestDto.propertyDto.MeterDto;
import com.building_mannager_system.dto.responseDto.ApiResponce;
import com.building_mannager_system.entity.customer_service.system_manger.Meter;
import com.building_mannager_system.service.system_service.MeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/meter")
public class MeterController {

    @Autowired
    private MeterService meterService;
    // Create a new Meter
    @PostMapping
    public ResponseEntity<ApiResponce<MeterDto>> createMeter(@RequestBody MeterDto meterDTO) {
        MeterDto createdMeterDTO = meterService.createMeter(meterDTO);

        // Wrap the result in ApiResponse
        ApiResponce<MeterDto> response = new ApiResponce<>(
                HttpStatus.CREATED.value(),
                createdMeterDTO,
                "Success"
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Get all Meters
    @GetMapping
    public ResponseEntity<ApiResponce<List<MeterDto>>> getAllMeters() {
        List<MeterDto> metersDTO = meterService.getAllMeters();

        // Wrap the result in ApiResponse
        ApiResponce<List<MeterDto>> response = new ApiResponce<>(
                HttpStatus.OK.value(),
                metersDTO,
                "Success"
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Get Meter by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponce<MeterDto>> getMeterById(@PathVariable Integer id) {
        MeterDto meterDTO = meterService.getMeterById(id);

        // Wrap the result in ApiResponse
        ApiResponce<MeterDto> response = new ApiResponce<>(
                HttpStatus.OK.value(),
                meterDTO,
                "Success"
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Update Meter by ID
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponce<MeterDto>> updateMeter(@PathVariable Integer id, @RequestBody MeterDto meterDTO) {
        MeterDto updatedMeterDTO = meterService.updateMeter(id, meterDTO);

        // Wrap the result in ApiResponse
        ApiResponce<MeterDto> response = new ApiResponce<>(
                HttpStatus.OK.value(),
                updatedMeterDTO,
                "Success"
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Delete Meter by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponce<Void>> deleteMeter(@PathVariable Integer id) {
        meterService.deleteMeter(id);

        // Wrap the result in ApiResponse
        ApiResponce<Void> response = new ApiResponce<>(
                HttpStatus.NO_CONTENT.value(),
                null,
                "Meter deleted successfully"
        );

        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

}
