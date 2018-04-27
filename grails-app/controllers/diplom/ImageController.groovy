package diplom

class ImageController {

    ImageService imageService

    def get(Long id) {
        byte[] bytes = imageService.getImage(id)
        response.outputStream << bytes
    }
}
