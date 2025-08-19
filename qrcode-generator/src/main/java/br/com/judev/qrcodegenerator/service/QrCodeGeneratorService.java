package br.com.judev.qrcodegenerator.service;

import br.com.judev.qrcodegenerator.dto.QrCodeGenerateRequest;
import br.com.judev.qrcodegenerator.dto.QrCodeGenerateResponse;
import br.com.judev.qrcodegenerator.ports.StoragePort;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class QrCodeGeneratorService {
    private final StoragePort storage;

    public QrCodeGeneratorService(StoragePort storage) {
        this.storage = storage;
    }

    public QrCodeGenerateResponse generateAndUploadQrCode(QrCodeGenerateRequest request) throws WriterException, IOException {

        // Cria QR Code
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(request.text(), BarcodeFormat.QR_CODE, 200, 200);

        // Converte em imagem
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
        byte[] qrCodeData = outputStream.toByteArray();

        // Gera nome de arquivo único
        String fileName = UUID.randomUUID().toString() + "." + "PNG";

        // Salva no storage
        String url = storage.uploadFile(qrCodeData, fileName, "image/" + "PNG");

        // Retorna resposta detalhada
        return new QrCodeGenerateResponse(url, fileName, "PNG", 200, 200);
    }
}
