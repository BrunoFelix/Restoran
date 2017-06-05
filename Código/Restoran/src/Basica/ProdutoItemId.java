package Basica;

import java.io.Serializable;

public class ProdutoItemId implements Serializable{
	
	public int getProduto() {
		return produto;
	}
	public void setProduto(int produto) {
		this.produto = produto;
	}
	public int getItemcomposicaoproduto() {
		return itemcomposicaoproduto;
	}
	public void setItemcomposicaoproduto(int itemcomposicaoproduto) {
		this.itemcomposicaoproduto = itemcomposicaoproduto;
	}
	private int produto;
    private int itemcomposicaoproduto;
    
    /*@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ProdutoItemId other = (ProdutoItemId) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }*/

}
