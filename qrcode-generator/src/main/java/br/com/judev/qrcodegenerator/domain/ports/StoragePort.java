package br.com.judev.qrcodegenerator.domain.ports;

public interface StoragePort {
    /**
     * Faz o upload de um arquivo e retorna a URL pública (ou fake).
     *
     * @param fileData   bytes do arquivo
     * @param fileName   nome do arquivo (ex: "qrcode.png")
     * @param contentType tipo do conteúdo (ex: "image/png")
     * @return URL onde o arquivo pode ser acessado
     */
    String uploadFile(byte[] fileData, String fileName, String contentType);
}
