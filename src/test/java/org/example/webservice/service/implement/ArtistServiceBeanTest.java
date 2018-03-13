package org.example.webservice.service.implement;

import org.example.webservice.converter.implement.ArtistConverter;
import org.example.webservice.dto.ArtistDTO;
import org.example.webservice.entities.Album;
import org.example.webservice.entities.Artist;
import org.example.webservice.repository.AlbumRepository;
import org.example.webservice.repository.ArtistRepository;
import org.example.webservice.service.ArtistService;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ArtistServiceBeanTest {
    private ArtistRepository artistRepoMock = mock(ArtistRepository.class);
    private AlbumRepository albumRepoMock = mock(AlbumRepository.class);
    private ArtistService artistService = new ArtistServiceBean(artistRepoMock, albumRepoMock, new ArtistConverter());

    @Test
    public void shouldReturnExistingArtistFromDB() {
        //  given...
        ArtistDTO artist = new ArtistDTO().setName("Tadzik Nalepa");

        //  when...
        when(artistRepoMock.findOneByName("Tadzik Nalepa")).thenReturn(
                Optional.ofNullable(new Artist().setName("Tadzik Nalepa").setId(1L)));

        //  then...
        ArtistDTO created = artistService.create(artist);
        Assert.assertNotNull(created);
        Assert.assertEquals(created.getName(), artist.getName());
    }

    @Test
    public void shouldCreateAndReturnNewArtist() {
        //  given...
        ArtistDTO newArtist = new ArtistDTO().setName("New Artist");

        //  when...
        when(artistRepoMock.findOneByName(Mockito.anyString())).thenReturn(Optional.empty());
        when(artistRepoMock.save(Mockito.any(Artist.class)))
                .thenReturn(new Artist().setId(1L).setName("New Artist"));

        //  then...
        ArtistDTO created = artistService.create(newArtist);
        Assert.assertNotNull(created);
        Assert.assertEquals(newArtist.getName(), created.getName());
    }

    @Test
    public void shouldRejectArtistAndReturnNull() {
        //  given...
        ArtistDTO newArtist = new ArtistDTO();

        //  when...
        when(artistRepoMock.findOneByName(Mockito.anyString())).thenReturn(Optional.empty());

        //  then...
        ArtistDTO created = artistService.create(newArtist);
        Assert.assertNull(created);
    }

//    @Test
//    public void shouldReturnExistingArtist() throws Exception {
//        //  given...
//        ArtistDTO newArtist = new ArtistDTO().setName("New Artist");
//
//        //  when...
//        when(albumRepoMock.findOneById(Mockito.anyLong())).thenReturn(Optional.of(new Album()));
//        when(albumRepoMock.save(Mockito.any(Album.class))).thenReturn(new Album());
//        when(artistRepoMock.findOneByName(Mockito.anyString()))
//                .thenReturn(Optional.ofNullable(new Artist().setId(1L).setName("New Artist")));
//
//        //  then...
//        Optional<ArtistDTO> created = artistService.createForAlbum(newArtist, Mockito.anyLong());
//        Assert.assertTrue(created.isPresent());
//    }
}