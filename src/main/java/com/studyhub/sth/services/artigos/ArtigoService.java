package com.studyhub.sth.services.artigos;

import com.studyhub.sth.dtos.artigos.ArtigoAtualizadoDto;
import com.studyhub.sth.dtos.artigos.ArtigoDto;
import com.studyhub.sth.dtos.artigos.NovoArtigoDto;
import com.studyhub.sth.dtos.tags.TagDto;
import com.studyhub.sth.entities.Artigo;
import com.studyhub.sth.entities.Tag;
import com.studyhub.sth.repositories.IArtigoRepositorio;
import com.studyhub.sth.repositories.ITagRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ArtigoService implements IArtigoService {

    @Autowired
    private IArtigoRepositorio artigoRepositorio;

    @Autowired
    private ITagRepositorio tagRepositorio;

    @Override
    public ArtigoDto criar(NovoArtigoDto novoArtigoDto) {
        Artigo artigo = new Artigo();
        artigo.setTitulo(novoArtigoDto.getTitulo());
        artigo.setConteudo(novoArtigoDto.getConteudo());
        artigo.setAutor(novoArtigoDto.getAutor());

        // Converte as tags recebidas para entidades Tag
        List<Tag> tags = novoArtigoDto.getTags().stream()
                                      .map(nome -> tagRepositorio.findByNome(nome)
                                      .orElseGet(() -> tagRepositorio.save(new Tag(null, nome))))
                                      .collect(Collectors.toList());

        artigo.setTags(tags);

        artigoRepositorio.save(artigo);

        return new ArtigoDto(artigo.getArtigoId(), artigo.getTitulo(), artigo.getConteudo(), artigo.getAutor(),
                             artigo.getTags().stream().map(tag -> new TagDto(tag.getTagId(), tag.getNome())).collect(Collectors.toList()));
    }

    @Override
    public ArtigoDto atualizar(UUID artigoId, ArtigoAtualizadoDto artigoAtualizadoDto) {
        Artigo artigo = artigoRepositorio.findById(artigoId).orElseThrow(() -> new RuntimeException("Artigo não encontrado"));

        artigo.setTitulo(artigoAtualizadoDto.getTitulo());
        artigo.setConteudo(artigoAtualizadoDto.getConteudo());
        artigo.setAutor(artigoAtualizadoDto.getAutor());

        // Atualiza as tags de forma semelhante
        List<Tag> tags = artigoAtualizadoDto.getTags().stream()
                                            .map(nome -> tagRepositorio.findByNome(nome)
                                            .orElseGet(() -> tagRepositorio.save(new Tag(null, nome))))
                                            .collect(Collectors.toList());

        artigo.setTags(tags);

        artigoRepositorio.save(artigo);

        return new ArtigoDto(artigo.getArtigoId(), artigo.getTitulo(), artigo.getConteudo(), artigo.getAutor(),
                             artigo.getTags().stream().map(tag -> new TagDto(tag.getTagId(), tag.getNome())).collect(Collectors.toList()));
    }

    @Override
    public ArtigoDto detalhar(UUID artigoId) {
        Artigo artigo = artigoRepositorio.findById(artigoId).orElseThrow(() -> new RuntimeException("Artigo não encontrado"));
        return new ArtigoDto(artigo.getArtigoId(), artigo.getTitulo(), artigo.getConteudo(), artigo.getAutor(),
                             artigo.getTags().stream().map(tag -> new TagDto(tag.getTagId(), tag.getNome())).collect(Collectors.toList()));
    }

    @Override
    public List<ArtigoDto> listarTodos() {
        List<Artigo> artigos = artigoRepositorio.findAll();
        return artigos.stream().map(artigo -> new ArtigoDto(artigo.getArtigoId(), artigo.getTitulo(), artigo.getConteudo(),
                                                           artigo.getAutor(), artigo.getTags().stream()
                                                           .map(tag -> new TagDto(tag.getTagId(), tag.getNome()))
                                                           .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    @Override
    public void deletar(UUID artigoId) {
        Artigo artigo = artigoRepositorio.findById(artigoId).orElseThrow(() -> new RuntimeException("Artigo não encontrado"));

    artigoRepositorio.delete(artigo);
}

}
