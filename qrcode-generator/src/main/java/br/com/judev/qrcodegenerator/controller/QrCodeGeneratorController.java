package br.com.judev.qrcodegenerator.controller;

import br.com.judev.qrcodegenerator.dto.QrCodeGenerateRequest;
import br.com.judev.qrcodegenerator.dto.QrCodeGenerateResponse;
import br.com.judev.qrcodegenerator.infra.storage.InMemoryStorage;
import br.com.judev.qrcodegenerator.service.QrCodeGeneratorService;
import com.google.zxing.WriterException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/qrcode")
public class QrCodeGeneratorController {

    private final QrCodeGeneratorService qrCodeGeneratorService;
    private final InMemoryStorage storage;

    public QrCodeGeneratorController(QrCodeGeneratorService qrCodeGeneratorService, InMemoryStorage storage) {
        this.qrCodeGeneratorService = qrCodeGeneratorService;
        this.storage = storage;
    }


    @GetMapping("/{fileName}")
    public ResponseEntity<byte[]> getFile(@PathVariable String fileName) {
        byte[] file = storage.getFile(fileName);

        if (file == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG) // sempre PNG no momento
                .body(file);
    }

    @PostMapping
    public ResponseEntity<QrCodeGenerateResponse> generate(@RequestBody QrCodeGenerateRequest request){
      try{
          QrCodeGenerateResponse response = qrCodeGeneratorService.generateAndUploadQrCode(request);
          return ResponseEntity.ok(response);
     }catch(WriterException | IOException ex){
          ex.printStackTrace();
          return ResponseEntity.internalServerError().build();
       }
    }
}
