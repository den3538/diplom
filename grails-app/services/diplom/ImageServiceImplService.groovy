package diplom

import grails.transaction.Transactional

@Transactional
class ImageServiceImplService implements ImageService {

    @Override
    byte[] getImage(Long id) {
        Photo.get(id).file
    }
}
