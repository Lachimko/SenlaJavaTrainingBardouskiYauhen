package menu.program.menuprocessor;

import menu.program.actions.parents.IAction;
import menu.program.menuelements.IMenu;

public interface IMenuBuilder {
	
	public abstract IMenu createRoot(String rootCaption);
	
	public abstract IMenu createMenuAndSetToParent(String menuCaption, IMenu parent, IAction actionWhichWillExecute);
	
	public abstract IMenu createMenuAndSetToParent(String menuCaption, IMenu parent);

	public abstract IMenu buildMenu();

}
