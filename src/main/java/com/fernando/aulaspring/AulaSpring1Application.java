package com.fernando.aulaspring;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fernando.aulaspring.dominio.Categorias;
import com.fernando.aulaspring.dominio.Cidade;
import com.fernando.aulaspring.dominio.Cliente;
import com.fernando.aulaspring.dominio.Endereco;
import com.fernando.aulaspring.dominio.Estado;
import com.fernando.aulaspring.dominio.ItemPedido;
import com.fernando.aulaspring.dominio.Pagamento;
import com.fernando.aulaspring.dominio.PagamentoComBoleto;
import com.fernando.aulaspring.dominio.PagamentoComCartao;
import com.fernando.aulaspring.dominio.Pedido;
import com.fernando.aulaspring.dominio.Produto;
import com.fernando.aulaspring.dominio.enums.EstadoPagamento;
import com.fernando.aulaspring.dominio.enums.TipoCliente;
import com.fernando.aulaspring.repositories.CategoriaRepository;
import com.fernando.aulaspring.repositories.CidadeRepository;
import com.fernando.aulaspring.repositories.ClienteRepository;
import com.fernando.aulaspring.repositories.EnderecoRepository;
import com.fernando.aulaspring.repositories.EstadoRepository;
import com.fernando.aulaspring.repositories.ItemPedidoRepositoty;
import com.fernando.aulaspring.repositories.PagamentoRepository;
import com.fernando.aulaspring.repositories.PedidoRepository;
import com.fernando.aulaspring.repositories.ProdutoRepository;

@SpringBootApplication
public class AulaSpring1Application implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(AulaSpring1Application.class, args);
	}
	
	@Autowired
	private CategoriaRepository categoriaRepoisitory ;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired ItemPedidoRepositoty itemPedidoRepositoty;
	
	@Override
	public void run(String... args) throws Exception {
		
		Categorias cat1 = new Categorias(null, "Informática");
		Categorias cat2 = new Categorias(null, "Escritório");
		Categorias cat3 = new Categorias(null, "Moveis");
		Categorias cat4 = new Categorias(null, "Alimentos");
		Categorias cat5 = new Categorias(null, "Cama mesa e banho");
		Categorias cat6 = new Categorias(null, "Cozinha");
		Categorias cat7 = new Categorias(null, "Roupa");
		Categorias cat8 = new Categorias(null, "Calçados");
		
		Produto p1 = new Produto(null,"Computador",2000.0);
		Produto p2 = new Produto(null,"Impressora",800.0);
		Produto p3 = new Produto(null,"Mouse",45.0);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		
		
		categoriaRepoisitory.saveAll(Arrays.asList(cat1,cat2, cat3, cat4, cat5, cat6, cat7, cat8));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "São Paulo", est2);
		Cidade c2 = new Cidade(null, "Uberlândia", est1);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c2));
		est2.getCidades().addAll(Arrays.asList(c1,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("27363323","93838393"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38280834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		SimpleDateFormat stf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null, stf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, stf.parse("10/10/2017 19:35"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, stf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1,p1,0.0, 1, 2000.0);
		ItemPedido ip2 = new ItemPedido(ped1,p3,0.0,2,80.0);
		ItemPedido ip3 = new ItemPedido(ped2,p2,100.0,1,800.0);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		
		itemPedidoRepositoty.saveAll(Arrays.asList(ip1,ip2,ip3));
		
		
		
		
		
	}

}
