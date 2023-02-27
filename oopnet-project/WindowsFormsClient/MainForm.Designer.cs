
namespace WindowsFormsClient
{
    partial class MainForm
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(MainForm));
            this.menuStrip = new System.Windows.Forms.MenuStrip();
            this.rankingsToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.playerToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiGoals = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiYellowCards = new System.Windows.Forms.ToolStripMenuItem();
            this.spectatorsToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiAttendance = new System.Windows.Forms.ToolStripMenuItem();
            this.toolsToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiSettings = new System.Windows.Forms.ToolStripMenuItem();
            this.ddlFavouriteTeam = new System.Windows.Forms.ComboBox();
            this.lblFavouriteTeam = new System.Windows.Forms.Label();
            this.lblFavouritePlayers = new System.Windows.Forms.Label();
            this.lblAllPlayers = new System.Windows.Forms.Label();
            this.flpFavouritePlayers = new System.Windows.Forms.FlowLayoutPanel();
            this.flpPlayers = new System.Windows.Forms.FlowLayoutPanel();
            this.menuStrip.SuspendLayout();
            this.SuspendLayout();
            // 
            // menuStrip
            // 
            this.menuStrip.ImageScalingSize = new System.Drawing.Size(28, 28);
            this.menuStrip.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.rankingsToolStripMenuItem,
            this.toolsToolStripMenuItem});
            resources.ApplyResources(this.menuStrip, "menuStrip");
            this.menuStrip.Name = "menuStrip";
            // 
            // rankingsToolStripMenuItem
            // 
            this.rankingsToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.playerToolStripMenuItem,
            this.spectatorsToolStripMenuItem});
            resources.ApplyResources(this.rankingsToolStripMenuItem, "rankingsToolStripMenuItem");
            this.rankingsToolStripMenuItem.Name = "rankingsToolStripMenuItem";
            // 
            // playerToolStripMenuItem
            // 
            this.playerToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tsmiGoals,
            this.tsmiYellowCards});
            this.playerToolStripMenuItem.Name = "playerToolStripMenuItem";
            resources.ApplyResources(this.playerToolStripMenuItem, "playerToolStripMenuItem");
            // 
            // tsmiGoals
            // 
            this.tsmiGoals.Name = "tsmiGoals";
            resources.ApplyResources(this.tsmiGoals, "tsmiGoals");
            this.tsmiGoals.Click += new System.EventHandler(this.tsmiGoals_Click);
            // 
            // tsmiYellowCards
            // 
            this.tsmiYellowCards.Name = "tsmiYellowCards";
            resources.ApplyResources(this.tsmiYellowCards, "tsmiYellowCards");
            this.tsmiYellowCards.Click += new System.EventHandler(this.tsmiYellowCards_Click);
            // 
            // spectatorsToolStripMenuItem
            // 
            this.spectatorsToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tsmiAttendance});
            this.spectatorsToolStripMenuItem.Name = "spectatorsToolStripMenuItem";
            resources.ApplyResources(this.spectatorsToolStripMenuItem, "spectatorsToolStripMenuItem");
            // 
            // tsmiAttendance
            // 
            this.tsmiAttendance.Name = "tsmiAttendance";
            resources.ApplyResources(this.tsmiAttendance, "tsmiAttendance");
            this.tsmiAttendance.Click += new System.EventHandler(this.tsmiAttendance_Click);
            // 
            // toolsToolStripMenuItem
            // 
            this.toolsToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tsmiSettings});
            this.toolsToolStripMenuItem.Name = "toolsToolStripMenuItem";
            resources.ApplyResources(this.toolsToolStripMenuItem, "toolsToolStripMenuItem");
            // 
            // tsmiSettings
            // 
            this.tsmiSettings.Name = "tsmiSettings";
            resources.ApplyResources(this.tsmiSettings, "tsmiSettings");
            this.tsmiSettings.Click += new System.EventHandler(this.tsmiSettings_Click);
            // 
            // ddlFavouriteTeam
            // 
            this.ddlFavouriteTeam.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.ddlFavouriteTeam.FormattingEnabled = true;
            resources.ApplyResources(this.ddlFavouriteTeam, "ddlFavouriteTeam");
            this.ddlFavouriteTeam.Name = "ddlFavouriteTeam";
            this.ddlFavouriteTeam.SelectedIndexChanged += new System.EventHandler(this.ddlFavouriteTeam_SelectedIndexChanged);
            // 
            // lblFavouriteTeam
            // 
            resources.ApplyResources(this.lblFavouriteTeam, "lblFavouriteTeam");
            this.lblFavouriteTeam.Name = "lblFavouriteTeam";
            // 
            // lblFavouritePlayers
            // 
            resources.ApplyResources(this.lblFavouritePlayers, "lblFavouritePlayers");
            this.lblFavouritePlayers.Name = "lblFavouritePlayers";
            // 
            // lblAllPlayers
            // 
            resources.ApplyResources(this.lblAllPlayers, "lblAllPlayers");
            this.lblAllPlayers.Name = "lblAllPlayers";
            // 
            // flpFavouritePlayers
            // 
            this.flpFavouritePlayers.AllowDrop = true;
            resources.ApplyResources(this.flpFavouritePlayers, "flpFavouritePlayers");
            this.flpFavouritePlayers.Name = "flpFavouritePlayers";
            this.flpFavouritePlayers.DragDrop += new System.Windows.Forms.DragEventHandler(this.flp_DragDrop);
            this.flpFavouritePlayers.DragEnter += new System.Windows.Forms.DragEventHandler(this.flp_DragEnter);
            // 
            // flpPlayers
            // 
            this.flpPlayers.AllowDrop = true;
            resources.ApplyResources(this.flpPlayers, "flpPlayers");
            this.flpPlayers.Name = "flpPlayers";
            this.flpPlayers.DragDrop += new System.Windows.Forms.DragEventHandler(this.flp_DragDrop);
            this.flpPlayers.DragEnter += new System.Windows.Forms.DragEventHandler(this.flp_DragEnter);
            // 
            // MainForm
            // 
            resources.ApplyResources(this, "$this");
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.lblFavouritePlayers);
            this.Controls.Add(this.lblAllPlayers);
            this.Controls.Add(this.flpFavouritePlayers);
            this.Controls.Add(this.flpPlayers);
            this.Controls.Add(this.ddlFavouriteTeam);
            this.Controls.Add(this.lblFavouriteTeam);
            this.Controls.Add(this.menuStrip);
            this.KeyPreview = true;
            this.MainMenuStrip = this.menuStrip;
            this.Name = "MainForm";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.MainForm_FormClosing);
            this.Load += new System.EventHandler(this.MainForm_Load);
            this.KeyDown += new System.Windows.Forms.KeyEventHandler(this.MainForm_KeyDown);
            this.menuStrip.ResumeLayout(false);
            this.menuStrip.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.MenuStrip menuStrip;
        private System.Windows.Forms.ToolStripMenuItem rankingsToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem playerToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem tsmiGoals;
        private System.Windows.Forms.ToolStripMenuItem tsmiYellowCards;
        private System.Windows.Forms.ToolStripMenuItem spectatorsToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem tsmiAttendance;
        private System.Windows.Forms.ToolStripMenuItem toolsToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem tsmiSettings;
        private System.Windows.Forms.ComboBox ddlFavouriteTeam;
        private System.Windows.Forms.Label lblFavouriteTeam;
        private System.Windows.Forms.Label lblFavouritePlayers;
        private System.Windows.Forms.Label lblAllPlayers;
        private System.Windows.Forms.FlowLayoutPanel flpFavouritePlayers;
        private System.Windows.Forms.FlowLayoutPanel flpPlayers;
    }
}