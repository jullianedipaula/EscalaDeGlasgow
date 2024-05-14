package com.mycompany.escaladeglasgow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EscalaDeGlasgow extends JFrame {
    // declaração dos componentes da interface gráfica
    private final JComboBox<Integer> grauOcular, grauVerbal, grauMotor;
    private final JLabel resultadoPontuacao, resultadoClassificacao;
    private Paciente paciente; //armazena informações do paciente

    public EscalaDeGlasgow() {
        // Configurações básicas da janela principal
        setTitle("Escala de Coma de Glasgow");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); //centraliza o popup

        //entrada dos dados por meio de uma lista suspensa 
        Integer[] pontuacaOcular = {1, 2, 3, 4};
        grauOcular = new JComboBox<>(pontuacaOcular);

        Integer[] pontuacaoVerbal = {1, 2, 3, 4, 5};
        grauVerbal = new JComboBox<>(pontuacaoVerbal);

        Integer[] pontuacaoMotora = {1, 2, 3, 4, 5, 6};
        grauMotor = new JComboBox<>(pontuacaoMotora);

        //adiciona um botão clicável e configura o ActionListener
        JButton calcBtn = new JButton("Calcular Pontuação");
        calcBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        calcBtn.addActionListener(new CalcBtnListener());

        //exibe os componentes para receber os resultados a partir do JLabel
        resultadoPontuacao = new JLabel("Pontuação Total: ");
        resultadoPontuacao.setFont(new Font("Arial", Font.BOLD, 14));
        resultadoPontuacao.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        resultadoClassificacao = new JLabel("Classificação: ");
        resultadoClassificacao.setFont(new Font("Arial", Font.BOLD, 14));
        resultadoClassificacao.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        
        //cria o layout para receber o JComboBox
        JPanel painelDeEntrada = new JPanel(new GridLayout(4, 4));
        painelDeEntrada.setBorder(BorderFactory.createEmptyBorder(10, 10, -10, 10));
        painelDeEntrada.add(new JLabel("Resposta Ocular:"));
        painelDeEntrada.add(grauOcular);
        painelDeEntrada.add(new JLabel("Resposta Verbal:"));
        painelDeEntrada.add(grauVerbal);
        painelDeEntrada.add(new JLabel("Resposta Motora:"));
        painelDeEntrada.add(grauMotor);
        
        //cria o layout para exibir os resultados 
        JPanel painelDoResultado = new JPanel();
        painelDoResultado.setLayout(new BoxLayout(painelDoResultado, BoxLayout.Y_AXIS));
        painelDoResultado.add(calcBtn);
        painelDoResultado.add(Box.createVerticalStrut(20));
        painelDoResultado.add(resultadoPontuacao);
        painelDoResultado.add(resultadoClassificacao);

        //adiciona e direcionas os componentes do layout
        setLayout(new BorderLayout());
        add(painelDeEntrada, BorderLayout.NORTH);
        add(painelDoResultado, BorderLayout.CENTER);
    }
        //objeto para enviar os dados através do botão
    private class CalcBtnListener implements ActionListener {
        @Override //indica que o método tá sobrescrevendo um método da classe pai
        public void actionPerformed(ActionEvent e) {
            calcularPontuacao();
        }
    }
        //calcula a pontuação e exibe os resultados
    private void calcularPontuacao() {
        //responsável por criar um objeto com os valores do JComboBox
        paciente = new Paciente((Integer) grauOcular.getSelectedItem(),
                                 (Integer) grauVerbal.getSelectedItem(),
                                 (Integer) grauMotor.getSelectedItem());
        //calcula a pontuação e a classificação
        String classificacao = paciente.pontuacaoGlasgow();
        //atualiza os resultados
        resultadoPontuacao.setText("Pontuação Total: " + paciente.getPontuacaoTotal());
        resultadoClassificacao.setText("Classificação: " + classificacao);
    }
    //classe interna para representar o paciente
    private class Paciente {
        private final int pontuacaoOcular;
        private final int pontuacaoVerbal;
        private final int pontuacaoMotor;
        
        //inicializa as pontuações
        public Paciente(int pontuacaoOcular, int pontuacaoVerbal, int pontuacaoMotor) {
            this.pontuacaoOcular = pontuacaoOcular;
            this.pontuacaoVerbal = pontuacaoVerbal;
            this.pontuacaoMotor = pontuacaoMotor;
        }
        //recebe as pontuações individualmente
        public int getPontuacaoOcular() {
            return pontuacaoOcular;
        }

        public int getPontuacaoVerbal() {
            return pontuacaoVerbal;
        }

        public int getPontuacaoMotor() {
            return pontuacaoMotor;
        }

        public int getPontuacaoTotal() {
            return pontuacaoOcular + pontuacaoVerbal + pontuacaoMotor;
        }
        
        //calcula a classificação do paciente
        public String pontuacaoGlasgow() {
            int pontuacaoTotal = getPontuacaoTotal();
            
            if (pontuacaoTotal >= 13 && pontuacaoTotal <= 15) {
                return "Leve";
            } else if (pontuacaoTotal >= 9 && pontuacaoTotal <= 12) {
                return "Moderado";
            } else if (pontuacaoTotal >= 3 && pontuacaoTotal <= 8) {
                return "Grave";
            } else {
                return "Pontuação Inválida";
            }
        }
    }
        //responsável por inicializar a aplicação Swing
     public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EscalaDeGlasgow app = new EscalaDeGlasgow();
            app.setVisible(true);
       });
    }
}
