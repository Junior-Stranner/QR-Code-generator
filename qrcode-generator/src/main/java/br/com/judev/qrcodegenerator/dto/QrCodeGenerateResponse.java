package br.com.judev.qrcodegenerator.dto;

public record QrCodeGenerateResponse(
        String url,
        String fileName,
        String format,   // formato do QR Code (ex: PNG)
        int width,
        int height
) {}

